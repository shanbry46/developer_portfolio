using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using accutreat_api.Models;
using unirest_net.http;
using System.Web;

namespace accutreat_api.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class CommentController : ControllerBase
    {
        readonly AccutreatDbContext context;
        private readonly ILogger<CommentController> _logger;

        public CommentController(AccutreatDbContext context, ILogger<CommentController> logger)
        {
            this.context = context;
            _logger = logger;
        }

        // GET {baseurl}/comment
        // Response: 200 OK
        [HttpGet]
        public ActionResult Get()
        {
            return Ok(context.drug_comments.ToList());
        }

        // GET {baseurl}/comment/{drug_name}
        // Response: 201 Created
        [HttpGet("{drug_name}")]
        public ActionResult GetComment(string drug_name)
        {
            var comments = from comment in context.drug_comments
                           where (comment.drug_name.ToLower()).Contains(HttpUtility.UrlDecode(drug_name.ToLower()))
                           group comment by new
                           {
                               comment.drug_name,
                               comment.drug_comments
                           } into drugc
                           select new
                           {
                               drugc.Key.drug_name,
                               drugc.Key.drug_comments
                           };

            return Ok(comments.ToList());
        }


        // POST {baseurl}/comment
        // Response: 200 OK
        [HttpPost]
        public ActionResult CreateComment(DrugComment comments)
        {
            if (comments == null)
            {
                comments = new Models.DrugComment();
                comments.drug_name = Guid.NewGuid().ToString().ToLower();
                comments.drug_comments = "Tastes like chaulk";
            }
            context.Add(comments);
            context.SaveChanges();

            return Created("comment/" + comments.drug_name, comments);
        }
    }
}
