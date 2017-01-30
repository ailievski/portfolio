using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;
using System.Xml.Serialization;
using Windows.Data.Xml.Dom;

namespace LifeInsuranceAdvisor.Classes
{
    public static class ResultsClient
    {
        private static readonly HttpClient client;

        public static Uri ServerBaseUri
        {
            //#error Replace with your IP address 
            get { return new Uri("http://webmedia.mk/api-z-osiguruvanje/api/"); }
        }

        public static Boolean IsDirty { get; private set; }

        static ResultsClient()
        {
            IsDirty = true;
            client = new HttpClient(new DemoHttpMessageHandler());
        }

        public static async Task<List<RezultatModel>> GetResultsAsync(string gender, int vozrast, int period, int premija)
        {
            {
                var url = string.Format("Presmetka?gender={0}&vozrast={1}&period={2}&premija={3}", gender, vozrast, period, premija);

                var response = await client.GetAsync(new Uri(ServerBaseUri, url));
                if (response.IsSuccessStatusCode)
                {
                    List<RezultatModel> rez =  response.Content.ReadAsAsync<List<RezultatModel>>().Result;

                    return rez;
                }
                else
                {
                    var list = new List<RezultatModel>();
                    list.Add(new RezultatModel() { OsigurenaID = 1, OsigurenaSuma = 100 });
                    list.Add(new RezultatModel() { OsigurenaID = 2, OsigurenaSuma = 200 });
                    list.Add(new RezultatModel() { OsigurenaID = 3, OsigurenaSuma = 300 });
                    list.Add(new RezultatModel() { OsigurenaID = 4, OsigurenaSuma = 400 });

                    return list;
                }
            }
        }
    }
}
