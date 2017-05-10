using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ContactWeb.Models
{
    public class Contact
    {
        public int Id { get; set; }
        public Guid UserId { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public string PhoneNumber { get; set; }
        public string MobileNumber { get; set; }
        public DateTime Birthday { get; set; }
        public string StreetAddress { get; set; }
        public string Neightborhood { get; set; }
        public string City { get; set; }
        public string State { get; set; }
    }
}