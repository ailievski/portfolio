using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace LifeInsuranceAdvisor.Classes
{
    public class DemoHttpMessageHandler : HttpClientHandler
    {
        public static bool ShowKittens { get; set; }

        protected override Task<HttpResponseMessage> SendAsync(HttpRequestMessage request, System.Threading.CancellationToken cancellationToken)
        {
            if (!ShowKittens ||
                request.Method != HttpMethod.Get || 
                !request.RequestUri.OriginalString.EndsWith("1"))
                return base.SendAsync(request, cancellationToken);

            var model = new PresmetkaModel();
            model.Gender = "male";
            model.godiniNaLice = 20;
            model.GodiniOsiguruvanje = 3;
            model.Premija = 350;

            var response = new HttpResponseMessage(HttpStatusCode.OK);
            var json = JsonConvert.SerializeObject(model);
            response.Content = new StringContent(json);
            response.Content.Headers.ContentType = new System.Net.Http.Headers.MediaTypeHeaderValue("text/json");

            return Task.FromResult(response);
        }
    }
}
