namespace accutreat_api.Models
{
    using System.ComponentModel.DataAnnotations;
    using Newtonsoft.Json;

    public class User {

        [Key]
        [Required(ErrorMessage ="email is Required")]
        public string email { get; set; }

        [Required(ErrorMessage ="name is Required")]
        public string user_name { get; set; }

        [Required(ErrorMessage ="password hash is Required")]
        public string password_hash { get; set; }
                
        [Required(ErrorMessage ="zip code is Required")]
        public int zip_code { get; set; }
    }
}
