using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LifeInsuranceAdvisor.Classes
{
    public class RezultatModel
    {
        public int OsigurenaID { get; set; }
        public int OsigurenaSuma { get; set; }

        public RezultatModel()
        {
            OsigurenaID = 0;
            OsigurenaSuma = 0;
        }
    }
}
