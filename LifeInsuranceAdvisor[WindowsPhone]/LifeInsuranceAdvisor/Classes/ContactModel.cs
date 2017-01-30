using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LifeInsuranceAdvisor.Classes
{
    public class ContactModel
    {
        public string company { get; set; }
        public int age { get; set; }
        public int duration { get; set; }
        public int premium { get; set; }
        public int sum { get; set; }

        public ContactModel()
        {
            company = String.Empty;
            age = 0;
            duration = 0;
            premium = 0;
            sum = 0;
        }
    }
}
