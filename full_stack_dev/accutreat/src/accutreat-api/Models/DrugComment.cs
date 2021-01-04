using System;
using System.ComponentModel.DataAnnotations;

namespace accutreat_api.Models
{
    public class DrugComment {

        [Key]
        public int dc_id { get; set; }

        [Required(ErrorMessage ="drug name is required")]
        public string drug_name { get; set; }

        [Required(ErrorMessage ="comments are required")]
        public string drug_comments { get; set; }

    }
}