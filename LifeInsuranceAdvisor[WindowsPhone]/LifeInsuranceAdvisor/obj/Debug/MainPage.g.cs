﻿

#pragma checksum "C:\Users\Andrej\Desktop\LIA\LifeInsuranceAdvisor_v3\LifeInsuranceAdvisor\MainPage.xaml" "{406ea660-64cf-4c82-b6f0-42d48172a799}" "9B58690118970F7DC7AB9E950EADD469"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace LifeInsuranceAdvisor
{
    partial class MainPage : global::Windows.UI.Xaml.Controls.Page, global::Windows.UI.Xaml.Markup.IComponentConnector
    {
        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Windows.UI.Xaml.Build.Tasks"," 4.0.0.0")]
        [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
 
        public void Connect(int connectionId, object target)
        {
            switch(connectionId)
            {
            case 1:
                #line 98 "..\..\MainPage.xaml"
                ((global::Windows.UI.Xaml.Controls.Primitives.Selector)(target)).SelectionChanged += this.AgeComboBox_SelectionChanged;
                 #line default
                 #line hidden
                break;
            case 2:
                #line 144 "..\..\MainPage.xaml"
                ((global::Windows.UI.Xaml.Controls.Primitives.ButtonBase)(target)).Click += this.CalculateButton_Click;
                 #line default
                 #line hidden
                break;
            case 3:
                #line 155 "..\..\MainPage.xaml"
                ((global::Windows.UI.Xaml.Controls.Primitives.ButtonBase)(target)).Click += this.Cpp_Click;
                 #line default
                 #line hidden
                break;
            case 4:
                #line 156 "..\..\MainPage.xaml"
                ((global::Windows.UI.Xaml.Controls.Primitives.ButtonBase)(target)).Click += this.AboutUs_Click;
                 #line default
                 #line hidden
                break;
            case 5:
                #line 157 "..\..\MainPage.xaml"
                ((global::Windows.UI.Xaml.Controls.Primitives.ButtonBase)(target)).Click += this.Contact_Click;
                 #line default
                 #line hidden
                break;
            }
            this._contentLoaded = true;
        }
    }
}


