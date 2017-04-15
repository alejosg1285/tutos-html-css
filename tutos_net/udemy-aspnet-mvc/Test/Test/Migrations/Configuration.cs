using ContactWeb.Models;

namespace ContactWeb.Migrations
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;

    internal sealed class Configuration : DbMigrationsConfiguration<ContactWeb.Models.ContactWebContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;
            ContextKey = "ContactWeb.Models.ContactWebContext";
        }

        protected override void Seed(ContactWeb.Models.ContactWebContext context)
        {
            //  This method will be called after migrating to the latest version.

            //  You can use the DbSet<T>.AddOrUpdate() helper extension method 
            //  to avoid creating duplicate seed data. E.g.
            //
            //    context.People.AddOrUpdate(
            //      p => p.FullName,
            //      new Person { FullName = "Andrew Peters" },
            //      new Person { FullName = "Brice Lambson" },
            //      new Person { FullName = "Rowan Miller" }
            //    );
            //
            context.Contacts.AddOrUpdate(
                p=>p.Id, new Contact {Id = 1, Birthday =new DateTime(1935,05,30),City = "Kandor",Email = "superman@krypton.com", FirstName = "Kal", LastName = "El", MobileNumber = "324658792", Neightborhood = "Metropolis",PhoneNumber = "4343324",State = "Kansas",UserId = new Guid("7e16ed67-cdfe-494c-bc48-09b88c9a91ce") },
                new Contact { Id = 2, Birthday = new DateTime(1954, 05, 30), City = "Argo", Email = "supergirl@krypton.com", FirstName = "Kara", LastName = "Zor-El", MobileNumber = "324658792", Neightborhood = "Metropolis", PhoneNumber = "4343324", State = "Kansas", UserId = new Guid("7e16ed67-cdfe-494c-bc48-09b88c9a91ce") });
        }
    }
}
