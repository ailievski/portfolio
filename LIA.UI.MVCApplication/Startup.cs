using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(LIA.UI.MVCApplication.Startup))]
namespace LIA.UI.MVCApplication
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
