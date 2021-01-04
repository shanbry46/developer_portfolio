using System;
using System.ComponentModel.DataAnnotations;

namespace accutreat_api.Models
{
    public class DrugPrice {

        [Key]
        public int? dp_id { get; set; }

        [Required(ErrorMessage ="drug name is Required")]
        public string drug_name { get; set; }

        [Required(ErrorMessage ="zip code is Required")]
        public int zip_code { get; set; }

        [Required(ErrorMessage ="price is Required")]
        public double price { get; set; }

        public DateTime date { get; set; }

        [Required(ErrorMessage ="rxcui is Required")]
        public string rxcui { get; set; }

        public int quantity { get; set; }
    }
}