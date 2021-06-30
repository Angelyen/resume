using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

        
        private void button1_Click(object sender, EventArgs e)
        {

            
            Form1 f = new Form1(); //創建子視窗
            this.Visible = false;//將Form1隱藏。由於在Form1的程式碼內使用this，所以this為Form1的物件本身
            f.Visible = true;//顯示第二個視窗

        }

        private void button1_MouseEnter(object sender, EventArgs e)
        {
            button1.ForeColor = Color.Transparent;//前景
            button1.BackColor = Color.Transparent;//去背景
            button1.FlatAppearance.BorderSize = 0;//去邊線
            button1.FlatAppearance.MouseOverBackColor = Color.Transparent;//鼠標經過
            //button1.FlatAppearance.MouseDownBackColor = Color.Transparent;//鼠標按下
            //button1.BackColor = Color.Empty;
        }
    }
}
