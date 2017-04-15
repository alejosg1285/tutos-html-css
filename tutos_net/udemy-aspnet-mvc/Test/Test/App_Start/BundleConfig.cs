using System.Web;
using System.Web.Optimization;

namespace Test
{
    public class BundleConfig
    {
        // Para obtener más información sobre Bundles, visite http://go.microsoft.com/fwlink/?LinkId=301862
        public static void RegisterBundles(BundleCollection bundles)
        {
            bundles.Add(new ScriptBundle("~/bundles/jquery").Include(
                        "~/js/lib/jquery-3.1.1.slim.min.js"));

            bundles.Add(new ScriptBundle("~/bundles/bootstrap").Include(
                      "~/js/lib/bootstrap.min.js"));

            // Utilice la versión de desarrollo de Modernizr para desarrollar y obtener información. De este modo, estará
            // preparado para la producción y podrá utilizar la herramienta de compilación disponible en http://modernizr.com para seleccionar solo las pruebas que necesite.
            /*bundles.Add(new ScriptBundle("~/bundles/modernizr").Include(
                        "~/Scripts/modernizr-*"));*/
            bundles.Add(new ScriptBundle("~/bundles/datatables").Include(
                "~/js/lib/jquery.dataTables.min.js", 
                "~/js/lib/dataTables.bootstrap.min.js", 
                "~/js/lib/dataTables.colReorder.min.js", 
                "~/js/lib/dataTables.responsive.min.js"));

            bundles.Add(new ScriptBundle("~/bundles/jqueryui").Include(
                "~/js/lib/jquery-ui.min.js"
                ));

            bundles.Add(new StyleBundle("~/Content/css").Include(
                      "~/css/bootstrap.min.css",
                      "~/css/jquery.dataTables.min.css",
                      "~/css/jquery.dataTables_themeroller.css",
                      "~/css/dataTables.bootstrap.min.css",
                      "~/css/colReorder.bootstrap.min.css",
                      "~/css/responsive.bootstrap.min.css",
                      "~/css/responsive.dataTables.min.css",
                      "~/css/jquery-ui.min.css",
                      "~/css/jquery-ui.structure.min.css",
                      "~/css/jquery-ui.theme.min.css",
                      "~/css/site.css"));
        }
    }
}
