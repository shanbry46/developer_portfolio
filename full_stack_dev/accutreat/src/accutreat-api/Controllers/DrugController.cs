using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using accutreat_api.Models;
using unirest_net.http;

/*
*
*   This class is used for accessing the RxNorm API when a user wants to contribute a drug price they paid.
*
*/

namespace accutreat_api.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class DrugController : Controller
    {
        readonly AccutreatDbContext context;
        private readonly ILogger<DrugController> _logger;
        public DrugController(AccutreatDbContext context, ILogger<DrugController> logger) {
            this.context = context;
            _logger = logger;
        }

        // GET api/drug/{drug_name}
        // Response: 200 OK
        [HttpGet("{drug_name}")]
        public ActionResult GetDrugsByName(string drug_name)
        {
            HttpResponse<string> response = Unirest.get("https://rxnav.nlm.nih.gov/REST/drugs?name="+drug_name)
              .header("Accept", "application/json")
              .asJson<string>();

            var drugs = response.Body;
            
            return Ok(drugs);
        }


        /*****
        *
        * The below CRUD functions are for drug_prices DB
        */

        // GET api/drug
        // Response: 200 OK
        [HttpGet]
        public ActionResult Get()
        {
            return Ok(context.drug_price.ToList());
        }

        // GET api/drug/{drug_name}/price
        // Response: 200 OK
        [HttpGet("{drug_name}/price")]
        public ActionResult GetDrug(string drug_name, [FromQuery] int zip_code = 0)
        {
            if (zip_code > 0)
            {
                var drugs = from drug in context.drug_price
                            where (drug.drug_name.ToLower()).Contains(drug_name.ToLower())  &&
                            drug.zip_code == zip_code
                            group drug by new
                            {
                                drug.zip_code,
                                drug.rxcui,
                                drug.drug_name,
                                drug.quantity
                            } into tdrug
                            select new
                            {
                                price = tdrug.Average(d => d.price),
                                tdrug.Key.zip_code,
                                tdrug.Key.rxcui,
                                tdrug.Key.drug_name,
                                tdrug.Key.quantity
                            };
                //var drugs = context.drug_price.Where(d => (d.drug_name == drug_name) && (d.zip_code == zip_code));
                return Ok(drugs);
            }
            else{
                var drugs = from drug in context.drug_price
                            where (drug.drug_name.ToLower()).Contains(drug_name.ToLower())
                            group drug by new
                            {
                                drug.zip_code,
                                drug.rxcui,
                                drug.drug_name,
                                drug.quantity
                            } into tdrug
                            select new
                            {
                                price = tdrug.Average(d => d.price),
                                tdrug.Key.zip_code,
                                tdrug.Key.rxcui,
                                tdrug.Key.drug_name,
                                tdrug.Key.quantity
                            };
                //var drugs = context.drug_price.Where(d => d.drug_name == drug_name);
                return Ok(drugs);
            }
        }

        // POST api/drug/{drug_name}/price/{uid}
        // Response: 201 Created
        [HttpPost]
        public ActionResult CreateDrugPrice(DrugPrice drug)
        {
            if (drug == null)
            {
                drug = new Models.DrugPrice();
                drug.dp_id = Guid.NewGuid().GetHashCode();
                drug.drug_name = "Morphine";
                drug.zip_code = 32814;
                drug.price = 8.99;
                drug.date = DateTime.Now;
                drug.rxcui = "12345";
            }

            context.Add(drug);
            context.SaveChanges();

            return  Created("drug/"+drug.drug_name+"/price/"+drug.dp_id,drug);
        }

        // DELETE api/drug/{drug_name}/price/{dp_id}
        // Response: 204 No Content
        [HttpDelete]
        public ActionResult Delete(int drug_id)
        {
            var drug = context.drug_price.Find(drug_id);
            
            if (drug != null)
            {
                context.drug_price.Remove(drug);
                context.SaveChanges();
            }
            return NoContent();
        }
    }
}