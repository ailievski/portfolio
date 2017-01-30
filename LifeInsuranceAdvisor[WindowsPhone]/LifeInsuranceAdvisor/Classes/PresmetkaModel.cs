using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LifeInsuranceAdvisor
{
    public class PresmetkaModel
    {
        public string Gender { get; set; }
        public int godiniNaLice { get; set; }
        public int GodiniOsiguruvanje { get; set; }
        public int Premija { get; set; }

        public PresmetkaModel()
        {
            this.Gender = String.Empty;
            this.godiniNaLice = 0;
            this.GodiniOsiguruvanje = 0;
            this.Premija = 0;
        }

    }

}
