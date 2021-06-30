using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        StreamReader inputFile;

        int column = 0;

        Pillcsv[] pill = new Pillcsv[56] ;

        string path = @"pill.csv";
        //string path = @"C:\Users\Administrator\Desktop\pill.csv";
        public Form1()
        {
            InitializeComponent();
            
            
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            
            inputFile = File.OpenText(path);
   
            int row=0 ;
            while (!inputFile.EndOfStream)
            {
                String line = inputFile.ReadLine();
                pill[row] = new Pillcsv();
                pill[row].setpill(line);             
                row++;
            }
            
        }

        private void comboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            
            String pill_name = comboBox.SelectedItem.ToString();
            int index = 0;
            for(int i = 0; i < pill.Length; i++)
            {
                if (pill[i].getename().Equals(pill_name))
                    index = i;

            }


            
            cname_label.Text =pill[index].getcname().ToString();
            description_label.Text = pill[index].getdescription().ToString();
            note_label.Text = pill[index].getnote().ToString();
            symptom_label.Text = pill[index].getsymptom().ToString();
            effect_label.Text = pill[index].geteffect().ToString();
            pictureBox.Image = pillList.Images[index];
            
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            this.Visible = false;
            Form2 form2 = new Form2();
            form2.Visible = true;
        }

    }
}
