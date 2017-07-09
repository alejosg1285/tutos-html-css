namespace Vidly.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class SeedUsers : DbMigration
    {
        public override void Up()
        {
            Sql(@"
INSERT INTO [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName]) VALUES (N'69cb3c1a-3781-451f-916a-eb7ee621fc66', N'admin@vidly.com', 0, N'APlx2r++YE/hP/YVW+JFw22LJFmmtEfPqNj1XN7URtajYdPYAFwK2TcMVgsxVJJvfQ==', N'4c6b0f17-e1c3-4b03-b9c4-205f7d296781', NULL, 0, 0, NULL, 1, 0, N'admin@vidly.com')
INSERT INTO [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName]) VALUES (N'ecf3c567-d14b-43ea-960a-d6491a8c1c17', N'guest@vidly.com', 0, N'AJgus2vDy2V51OlSdoiyHxyqyV7El1NYP9akfiWMVmZMZnIYe7/qjvFeqJbrNUBNEQ==', N'57a64f5e-5d31-4c6e-a187-28cf89f830ef', NULL, 0, 0, NULL, 1, 0, N'guest@vidly.com')

INSERT INTO [dbo].[AspNetRoles] ([Id], [Name]) VALUES (N'9ef38ff2-077a-4c6d-a832-7312f8f3f44a', N'CanManageMovies')

INSERT INTO [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'69cb3c1a-3781-451f-916a-eb7ee621fc66', N'9ef38ff2-077a-4c6d-a832-7312f8f3f44a')
");
        }
        
        public override void Down()
        {
        }
    }
}
