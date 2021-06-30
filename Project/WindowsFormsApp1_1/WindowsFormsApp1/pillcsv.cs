using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1
{
    class Pillcsv
    {
        //private static String line;
        private static char[] delim = { ',' };

        String ename;
        String cname;
        String description;
        String symptom;
        String effect;
        String note;
        public Pillcsv()
        {
            ename = this.ename;
            cname = this.cname;
            description = this.description;
            symptom = this.symptom;
            effect = this.effect;
            note = this.note;
        }


        public void setpill(string line)
        {
            String[] p = line.Split(delim);
            ename = p[0];
            cname = p[1];
            description = p[2];
            note = p[3];
            symptom = p[4];
            effect = p[5];
        }
        public String getename()
        {
            return ename;
        }

        public String getcname()
        {
            return cname;
        }
        public String getdescription()
        {
            return description;
        }   
        public String getnote()
        {
            return note;
        }
        public String getsymptom()
        {
            return symptom;
        }
        public String geteffect()
        {
            return effect;
        }
       

    }
}
