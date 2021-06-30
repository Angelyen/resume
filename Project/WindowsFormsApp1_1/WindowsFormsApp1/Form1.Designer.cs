namespace WindowsFormsApp1
{
    partial class Form1
    {
        /// <summary>
        /// 設計工具所需的變數。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清除任何使用中的資源。
        /// </summary>
        /// <param name="disposing">如果應該處置受控資源則為 true，否則為 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 設計工具產生的程式碼

        /// <summary>
        /// 此為設計工具支援所需的方法 - 請勿使用程式碼編輯器修改
        /// 這個方法的內容。
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.label1 = new System.Windows.Forms.Label();
            this.comboBox = new System.Windows.Forms.ComboBox();
            this.pictureBox = new System.Windows.Forms.PictureBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.effect_label = new System.Windows.Forms.Label();
            this.symptom_label = new System.Windows.Forms.Label();
            this.description_label = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.cname_label = new System.Windows.Forms.Label();
            this.pillList = new System.Windows.Forms.ImageList(this.components);
            this.note_label = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.BackColor = System.Drawing.Color.Transparent;
            this.label1.Font = new System.Drawing.Font("標楷體", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.label1.Location = new System.Drawing.Point(74, 66);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(130, 24);
            this.label1.TabIndex = 1;
            this.label1.Text = "藥物名稱 :";
            // 
            // comboBox
            // 
            this.comboBox.Font = new System.Drawing.Font("標楷體", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.comboBox.FormattingEnabled = true;
            this.comboBox.Items.AddRange(new object[] {
            "A.T. TABLETS 500MG (ACETAMINOPHEN) LITA",
            "ABROXOL TABLETS 30MG (AMBROXOL HYDROCHLORIDE) JOHNSON",
            "ACETAL TABLET 500MG (ACETAMINOPHEN) PURZER",
            "ACETAMINO TABLETS 500MG P.L.(ACETAMINOPHEN)",
            "ACETAMINOPHEN TABLETS 0.3 GM F.Y",
            "ACETAMINOPHEN TABLETS 300MG YU SHENG",
            "ACETAMINOPHEN TABLETS 325MG ASTAR",
            "ACETAMINOPHEN TABLETS 325MG C.T",
            "ACETAMINOPHEN TABLETS 500MG ASTAR",
            "ACETAMINOPHEN TABLETS C.R",
            "ACETAMINOPHEN TABLETS Vita",
            "AMBOL TABLETS 30MG (AMBROXOL) M.S.",
            "AMBOSO TABLETS 30MG (AMBROXOL) EVEREST",
            "AMBRON TABLET 30MG (AMBROXOL) F.Y",
            "AMBROVAN TABLET 30MG (AMBROXOL) SHENG CHUN",
            "AMBROXOL TABLETS 30MG CHINTENG",
            "AMBROXOL TABLETS 30MG KOJAR",
            "AMBROXOL TABLETS 30MG SINTON",
            "ANTHOLIN CAPSULES WINSTON",
            "ARSOLINE TABLET 20MG _F.S._(TIPEPIDINE HIBENZATE)",
            "ASTIDIN TABLETS 20MG _EAYUNG_ (TIPEPIDINE HIBENZATE)",
            "B.H. TABLETS 8MG LITA",
            "B.H.L. TABLET 5MG",
            "B.H.L. TABLETS 2MG",
            "Bensau Soft Capsules 200mg _Lotus_",
            "BROSOU TABLETS 20MG _CHINTENG_ (TIPEPIDINE HIBENZATE)",
            "Caffeine",
            "CAPETAN TABLETS 30MG WEIDAR(CARBETAPENTANE)",
            "CARBETAPENTANE CITRATE S.C. TABLETS MEIDER",
            "Cetia",
            "COFEDENIN TABLETS _Y.C._ (TIPEPIDINE HIBENZATE)",
            "COSIN TABLETS 20MG KOJAR (NOSCAPINE)",
            "Cousel Tablets 20mg _KOJAR_(Tipepidine Hibenzate)",
            "DEBRONC TABLETS 50MG LITA",
            "Dextromethorphan",
            "EXSOL CAPSULES 100MG (OXOLAMINE CITRATE) Y.Y.",
            "LOTANLIN TABLETS 30MG (AMBROXOL) ASTAR",
            "MUBROXOL TABLETS 30MG (AMBROXOL) C.C.P.C",
            "MUCOBRON TAbLETS 30MG (AMBROXOL HYDROCHLORIDE) WEIDAR",
            "Noma Tablet H.H",
            "Norcol F.C. Tablets 20mg S.C",
            "NOSCAPINE TABLETS J.L.",
            "NOSCAPINE TABLETS 20MG ROOT",
            "OXO CAPSULES (OXOLAMINE CITRATE) YUNG SHIN",
            "OXY TABLETS 100MG S.T. (OXOLAMINE CITRATE)",
            "PSEUDOEPHEDRINE HCL TABLETS _HONTEN_",
            "Pseudoephedrine HCl Tablets 30mg",
            "Pseudoephedrine HCl Tablets 60mg",
            "PSEUDOEPHEDRINE TABLETS _CURIE_ ",
            "Rinderon",
            "SEUDORIN TABLETS (PSEUDOEPHEDRINE HCL)",
            "SOBEDINE TABLET 22.14MG _S.L_(TIPEPIDINE HIBENZATE)",
            "Tagament Famotidine ",
            "TYS804 ",
            "ZECOL TABLETS"});
            this.comboBox.Location = new System.Drawing.Point(210, 60);
            this.comboBox.Name = "comboBox";
            this.comboBox.Size = new System.Drawing.Size(760, 35);
            this.comboBox.TabIndex = 3;
            this.comboBox.SelectedIndexChanged += new System.EventHandler(this.comboBox_SelectedIndexChanged);
            // 
            // pictureBox
            // 
            this.pictureBox.BackColor = System.Drawing.Color.Transparent;
            this.pictureBox.Location = new System.Drawing.Point(77, 132);
            this.pictureBox.Name = "pictureBox";
            this.pictureBox.Size = new System.Drawing.Size(267, 267);
            this.pictureBox.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox.TabIndex = 4;
            this.pictureBox.TabStop = false;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.BackColor = System.Drawing.Color.Transparent;
            this.label2.Font = new System.Drawing.Font("標楷體", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.label2.Location = new System.Drawing.Point(376, 162);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(130, 24);
            this.label2.TabIndex = 5;
            this.label2.Text = "藥物描述 :";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.BackColor = System.Drawing.Color.Transparent;
            this.label3.Font = new System.Drawing.Font("標楷體", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.label3.Location = new System.Drawing.Point(376, 342);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(106, 24);
            this.label3.TabIndex = 6;
            this.label3.Text = "副作用 :";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.BackColor = System.Drawing.Color.Transparent;
            this.label4.Font = new System.Drawing.Font("標楷體", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.label4.Location = new System.Drawing.Point(376, 252);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(130, 24);
            this.label4.TabIndex = 7;
            this.label4.Text = "適應症狀 :";
            // 
            // effect_label
            // 
            this.effect_label.BackColor = System.Drawing.Color.Transparent;
            this.effect_label.Font = new System.Drawing.Font("標楷體", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.effect_label.Location = new System.Drawing.Point(540, 342);
            this.effect_label.Name = "effect_label";
            this.effect_label.Size = new System.Drawing.Size(450, 99);
            this.effect_label.TabIndex = 8;
            this.effect_label.Text = "....";
            // 
            // symptom_label
            // 
            this.symptom_label.BackColor = System.Drawing.Color.Transparent;
            this.symptom_label.Font = new System.Drawing.Font("標楷體", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.symptom_label.Location = new System.Drawing.Point(540, 252);
            this.symptom_label.Name = "symptom_label";
            this.symptom_label.Size = new System.Drawing.Size(450, 90);
            this.symptom_label.TabIndex = 9;
            this.symptom_label.Text = "....";
            // 
            // description_label
            // 
            this.description_label.BackColor = System.Drawing.Color.Transparent;
            this.description_label.Font = new System.Drawing.Font("標楷體", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.description_label.Location = new System.Drawing.Point(540, 162);
            this.description_label.Name = "description_label";
            this.description_label.Size = new System.Drawing.Size(450, 24);
            this.description_label.TabIndex = 10;
            this.description_label.Text = "....";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.BackColor = System.Drawing.Color.Transparent;
            this.label5.Font = new System.Drawing.Font("標楷體", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.label5.Location = new System.Drawing.Point(376, 118);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(130, 24);
            this.label5.TabIndex = 11;
            this.label5.Text = "中文藥名 :";
            // 
            // cname_label
            // 
            this.cname_label.BackColor = System.Drawing.Color.Transparent;
            this.cname_label.Font = new System.Drawing.Font("標楷體", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.cname_label.Location = new System.Drawing.Point(540, 117);
            this.cname_label.Name = "cname_label";
            this.cname_label.Size = new System.Drawing.Size(450, 37);
            this.cname_label.TabIndex = 12;
            this.cname_label.Text = "....";
            // 
            // pillList
            // 
            this.pillList.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("pillList.ImageStream")));
            this.pillList.TransparentColor = System.Drawing.Color.Transparent;
            this.pillList.Images.SetKeyName(0, "A.T. TABLETS 500MG (ACETAMINOPHEN) LITA.jpg");
            this.pillList.Images.SetKeyName(1, "ABROXOL TABLETS 30MG (AMBROXOL HYDROCHLORIDE) JOHNSON.jpg");
            this.pillList.Images.SetKeyName(2, "ACETAL TABLET 500MG (ACETAMINOPHEN) PURZER.jpg");
            this.pillList.Images.SetKeyName(3, "ACETAMINO TABLETS 500MG P.L.(ACETAMINOPHEN).jpg");
            this.pillList.Images.SetKeyName(4, "ACETAMINOPHEN TABLETS 0.3 GM F.Y.jpg");
            this.pillList.Images.SetKeyName(5, "ACETAMINOPHEN TABLETS 300MG YU SHENG.jpg");
            this.pillList.Images.SetKeyName(6, "ACETAMINOPHEN TABLETS 325MG ASTAR.jpg");
            this.pillList.Images.SetKeyName(7, "ACETAMINOPHEN TABLETS 325MG C.T.jpg");
            this.pillList.Images.SetKeyName(8, "ACETAMINOPHEN TABLETS 500MG ASTAR.jpg");
            this.pillList.Images.SetKeyName(9, "ACETAMINOPHEN TABLETS C.R.jpg");
            this.pillList.Images.SetKeyName(10, "ACETAMINOPHEN TABLETS Vita.jpg");
            this.pillList.Images.SetKeyName(11, "AMBOL TABLETS 30MG (AMBROXOL) _M.S._.jpg");
            this.pillList.Images.SetKeyName(12, "AMBOSO TABLETS 30MG (AMBROXOL) _EVEREST_.jpg");
            this.pillList.Images.SetKeyName(13, "AMBRON TABLET 30MG (AMBROXOL) _F.Y_.jpg");
            this.pillList.Images.SetKeyName(14, "AMBROVAN TABLET 30MG (AMBROXOL) _SHENG CHUN_.jpg");
            this.pillList.Images.SetKeyName(15, "AMBROXOL TABLETS 30MG _CHINTENG_.jpg");
            this.pillList.Images.SetKeyName(16, "AMBROXOL TABLETS 30MG _KOJAR_.jpg");
            this.pillList.Images.SetKeyName(17, "AMBROXOL TABLETS 30MG _SINTON_.jpg");
            this.pillList.Images.SetKeyName(18, "ANTHOLIN CAPSULES WINSTON.jpg");
            this.pillList.Images.SetKeyName(19, "ARSOLINE TABLET 20MG _F.S._(TIPEPIDINE HIBENZATE).jpg");
            this.pillList.Images.SetKeyName(20, "ASTIDIN TABLETS 20MG _EAYUNG_ (TIPEPIDINE HIBENZATE).jpg");
            this.pillList.Images.SetKeyName(21, "B.H. TABLETS 8MG _LITA_.jpg");
            this.pillList.Images.SetKeyName(22, "B.H.L. TABLET 5MG.jpg");
            this.pillList.Images.SetKeyName(23, "B.H.L. TABLETS 2MG.jpg");
            this.pillList.Images.SetKeyName(24, "Bensau Soft Capsules 200mg _Lotus_.jpg");
            this.pillList.Images.SetKeyName(25, "BROSOU TABLETS 20MG _CHINTENG_ (TIPEPIDINE HIBENZATE).jpg");
            this.pillList.Images.SetKeyName(26, "Caffeine.jpg");
            this.pillList.Images.SetKeyName(27, "CAPETAN TABLETS 30MG WEIDAR(CARBETAPENTANE).jpg");
            this.pillList.Images.SetKeyName(28, "CARBETAPENTANE CITRATE S.C. TABLETS MEIDER.jpg");
            this.pillList.Images.SetKeyName(29, "Cetia.jpg");
            this.pillList.Images.SetKeyName(30, "COFEDENIN TABLETS _Y.C._ (TIPEPIDINE HIBENZATE).jpg");
            this.pillList.Images.SetKeyName(31, "COSIN TABLETS 20MG KOJAR (NOSCAPINE).jpg");
            this.pillList.Images.SetKeyName(32, "Cousel Tablets 20mg _KOJAR_(Tipepidine Hibenzate).jpg");
            this.pillList.Images.SetKeyName(33, "DEBRONC TABLETS 50MG LITA.jpg");
            this.pillList.Images.SetKeyName(34, "Dextromethorphan.jpg");
            this.pillList.Images.SetKeyName(35, "EXSOL CAPSULES 100MG (OXOLAMINE CITRATE) Y.Y..jpg");
            this.pillList.Images.SetKeyName(36, "LOTANLIN TABLETS 30MG (AMBROXOL) ASTAR.jpg");
            this.pillList.Images.SetKeyName(37, "MUBROXOL TABLETS 30MG (AMBROXOL) C.C.P.C.jpg");
            this.pillList.Images.SetKeyName(38, "MUCOBRON TAbLETS 30MG (AMBROXOL HYDROCHLORIDE) WEIDAR.jpg");
            this.pillList.Images.SetKeyName(39, "Noma Tablet H.H.jpg");
            this.pillList.Images.SetKeyName(40, "Norcol F.C. Tablets 20mg S.C.jpg");
            this.pillList.Images.SetKeyName(41, "NOSCAPINE TABLETS 20MG ROOT.jpg");
            this.pillList.Images.SetKeyName(42, "NOSCAPINE TABLETS J.L..jpg");
            this.pillList.Images.SetKeyName(43, "OXO CAPSULES (OXOLAMINE CITRATE) YUNG SHIN.jpg");
            this.pillList.Images.SetKeyName(44, "OXY TABLETS 100MG S.T. (OXOLAMINE CITRATE).jpg");
            this.pillList.Images.SetKeyName(45, "PSEUDOEPHEDRINE HCL TABLETS _HONTEN_.jpg");
            this.pillList.Images.SetKeyName(46, "Pseudoephedrine HCl Tablets 30mg.jpg");
            this.pillList.Images.SetKeyName(47, "Pseudoephedrine HCl Tablets 60mg.jpg");
            this.pillList.Images.SetKeyName(48, "PSEUDOEPHEDRINE TABLETS _CURIE_.jpg");
            this.pillList.Images.SetKeyName(49, "Rinderon.jpg");
            this.pillList.Images.SetKeyName(50, "SEUDORIN TABLETS (PSEUDOEPHEDRINE HCL).jpg");
            this.pillList.Images.SetKeyName(51, "SOBEDINE TABLET 22.14MG _S.L_(TIPEPIDINE HIBENZATE).jpg");
            this.pillList.Images.SetKeyName(52, "Tagament Famotidine.jpg");
            this.pillList.Images.SetKeyName(53, "TYS804.jpg");
            this.pillList.Images.SetKeyName(54, "ZECOL TABLETS.jpg");
            // 
            // note_label
            // 
            this.note_label.BackColor = System.Drawing.Color.Transparent;
            this.note_label.Font = new System.Drawing.Font("標楷體", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.note_label.Location = new System.Drawing.Point(540, 207);
            this.note_label.Name = "note_label";
            this.note_label.Size = new System.Drawing.Size(450, 24);
            this.note_label.TabIndex = 14;
            this.note_label.Text = "....";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.BackColor = System.Drawing.Color.Transparent;
            this.label7.Font = new System.Drawing.Font("標楷體", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.label7.Location = new System.Drawing.Point(376, 207);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(130, 24);
            this.label7.TabIndex = 13;
            this.label7.Text = "藥物標記 :";
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackColor = System.Drawing.Color.Transparent;
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(975, 447);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(43, 37);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox1.TabIndex = 15;
            this.pictureBox1.TabStop = false;
            this.pictureBox1.Click += new System.EventHandler(this.pictureBox1_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(1034, 493);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.note_label);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.cname_label);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.description_label);
            this.Controls.Add(this.symptom_label);
            this.Controls.Add(this.effect_label);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.pictureBox);
            this.Controls.Add(this.comboBox);
            this.Controls.Add(this.label1);
            this.DoubleBuffered = true;
            this.Name = "Form1";
            this.Text = "藥物辨識系統";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox comboBox;
        private System.Windows.Forms.PictureBox pictureBox;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label effect_label;
        private System.Windows.Forms.Label symptom_label;
        private System.Windows.Forms.Label description_label;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label cname_label;
        private System.Windows.Forms.ImageList pillList;
        private System.Windows.Forms.Label note_label;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.PictureBox pictureBox1;
    }
}

