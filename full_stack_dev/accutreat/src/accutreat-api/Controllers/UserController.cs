using System;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using accutreat_api.Models;

namespace accutreat_api.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : ControllerBase
    {
        readonly AccutreatDbContext context;
        private readonly ILogger<UserController> _logger;

        public UserController(AccutreatDbContext context, ILogger<UserController> logger)
        {
            this.context = context;
            _logger = logger;
        }

        // GET {baseurl}/user
        // Response: 200 OK
        [HttpGet]
        public ActionResult GetUsers()
        {
            return Ok(context.users);
        }

        // GET {baseurl}/user/{email}
        // Response: 201 Created
        [HttpGet("{email}")]
        public ActionResult GetUser(string email)
        {
            var user = context.users.Find(email);
            if (user == null)
            {
                return NotFound();
            }
            return Ok(user);
        }


        // POST {baseurl}/user
        // Response: 200 OK
        [HttpPost]
        public ActionResult CreateUser(User user)
        {
            if(user == null)
            {
                user = new Models.User();
                user.email = Guid.NewGuid().ToString().ToUpper()+"@gmail.com";
                user.user_name = "Nicole Kulakowski";
                user.password_hash = "dg378f6afsdhj2998hds";
                user.zip_code = 30213;
            }
            context.Add(user);
            context.SaveChanges();

            return Created("user/"+user.email,user);
        }

        // DELETE {baseurl}/user/{user_email}
        // Response: 204 No Content
        [HttpDelete("{email}")]
        public ActionResult Delete(string email)
        {
            var user = context.users.Find(email);
            
            if (user == null)
            {
                context.users.Remove(user);
                context.SaveChanges();
            }
            return NoContent();
        }

        [HttpGet("login")]
        public ActionResult<User> Login(string email, string hash)
        {
            if(email == null || hash == null)
            {
                return this.BadRequest("email and hash must be present");
            }

            var user = this.context.users.Find(email);

            if (user == null || user.password_hash != hash)
            {
                return this.Unauthorized();
            }
            else
            {
                return user;
            }
        }
    }
}
