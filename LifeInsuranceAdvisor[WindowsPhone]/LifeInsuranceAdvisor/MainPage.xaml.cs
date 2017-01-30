using LifeInsuranceAdvisor.Classes;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Threading.Tasks;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using System.Net.Http;
using System.Text;
using Windows.UI.Popups;
using Windows.Phone.UI.Input;


namespace LifeInsuranceAdvisor
{
    public sealed partial class MainPage : Page
    {
        PresmetkaModel presmetkaModel;
        RezultatModel rezultatModel;

        public MainPage()
        {
            this.InitializeComponent();

            this.NavigationCacheMode = NavigationCacheMode.Required;

            presmetkaModel = new PresmetkaModel();

            rezultatModel = new RezultatModel();

            for (int i = 15; i <= 65; i++)
                AgeComboBox.Items.Add(i);
            AgeComboBox.SelectedIndex = 0;

            int[] premium = new int[] { 200, 250, 300, 350, 400, 450, 500, 600, 700, 800, 900, 1000, 1500, 2000, 2500, 3000 };
            foreach (int pr in premium)
                PremiumComboBox.Items.Add(pr);
            PremiumComboBox.SelectedIndex = 0;
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            HardwareButtons.BackPressed += HardwareButtons_BackPressed;
        }
   
        private void CalculateButton_Click(object sender, RoutedEventArgs e)
        {
            presmetkaModel = CreateModel();

            Frame.Navigate(typeof(Results), presmetkaModel);
        }

        private PresmetkaModel CreateModel()
        {
            PresmetkaModel model = new PresmetkaModel();

            var gender = RadioButtonGrid.Children.OfType<RadioButton>().FirstOrDefault(r => r.IsChecked == true);
            model.Gender = gender.Tag.ToString();

            int selectedIndex1 = AgeComboBox.SelectedIndex;
            int selectedValue1 = (int)AgeComboBox.SelectedValue;
            model.godiniNaLice = selectedValue1;

            int selectedIndex2 = DurationComboBox.SelectedIndex;
            int selectedValue2 = (int)DurationComboBox.SelectedValue;
            model.GodiniOsiguruvanje = selectedValue2;

            int selectedIndex3 = PremiumComboBox.SelectedIndex;
            int selectedValue3 = (int)PremiumComboBox.SelectedValue;
            model.Premija = selectedValue3;

            return model;
        }


        void HardwareButtons_BackPressed(object sender, BackPressedEventArgs e)
        {
            Frame rootFrame = Window.Current.Content as Frame;
            if (rootFrame != null && rootFrame.CanGoBack)
            {
                rootFrame.GoBack();
                e.Handled = true;
            }

        }

        private void Cpp_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(CPP));
        }

        private void AboutUs_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(AboutUs));
        }

        private void Contact_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(ContactUS));
        }

        private void AgeComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            int selectedIndex = AgeComboBox.SelectedIndex;
            int selectedValue = (int)AgeComboBox.SelectedValue;


            if (selectedValue <= 50)
            {
                DurationComboBox.Items.Clear();
                for (int i = 10; i <= 25; i++)
                    DurationComboBox.Items.Add(i);
                DurationComboBox.SelectedIndex = 0;
            }
            else
            {
                switch (selectedValue)
                {
                    case 51:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 24; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 52:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 23; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 53:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 22; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 54:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 21; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 55:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 20; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 56:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 19; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 57:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 18; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 58:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 17; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 59:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 16; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 60:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 15; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 61:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 14; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 62:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 13; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 63:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 12; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 64:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 11; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                    case 65:
                        DurationComboBox.Items.Clear();
                        for (int i = 10; i <= 10; i++)
                            DurationComboBox.Items.Add(i);
                        DurationComboBox.SelectedIndex = 0;
                        break;
                }
            }
        }
    }
}
