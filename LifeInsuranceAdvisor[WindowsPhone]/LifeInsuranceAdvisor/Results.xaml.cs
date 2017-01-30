using LifeInsuranceAdvisor.Classes;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Threading.Tasks;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.Phone.UI.Input;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;


namespace LifeInsuranceAdvisor
{
    public sealed partial class Results : Page
    {
        PresmetkaModel presmetka;
        ContactModel kontakt;
        List<RezultatModel> rezultat;

        public Results()
        {
            this.InitializeComponent();

        }

        protected override async void OnNavigatedTo(NavigationEventArgs e)
        {
            HardwareButtons.BackPressed += HardwareButtons_BackPressed;

            presmetka = (PresmetkaModel)e.Parameter;

            rezultat = await ResultsClient.GetResultsAsync(presmetka.Gender, presmetka.godiniNaLice, presmetka.GodiniOsiguruvanje, presmetka.Premija);
            if (rezultat[1].OsigurenaSuma != 0)
            {
                CroatiaOS.Text = String.Format("{0} €", rezultat[1].OsigurenaSuma.ToString());
            }
            else
            {
                CroatiaOS.Text = "нема понуда";
            }
            if (rezultat[2].OsigurenaSuma != 0)
            {
                UniqaOS.Text = String.Format("{0} €", rezultat[2].OsigurenaSuma.ToString());
            }
            else
            {
                UniqaOS.Text = "нема понуда";
            }
            if (rezultat[3].OsigurenaSuma != 0)
            {
                WinnerOS.Text = String.Format("{0} €", rezultat[3].OsigurenaSuma.ToString());
            }
            else
            {
                WinnerOS.Text = "нема понуда";
            }
            if (rezultat[0].OsigurenaSuma != 0)
            {
                GraweOS.Text = String.Format("{0} €", rezultat[0].OsigurenaSuma.ToString());
            }
            else
            {
                GraweOS.Text = "нема понуда";
            }

            int[] premium = new int[] { 200, 250, 300, 350, 400, 450, 500, 600, 700, 800, 900, 1000, 1500, 2000, 2500, 3000 };
            foreach (int pr in premium)
                ChangePremiumComboBox.Items.Add(pr);

            if(presmetka.godiniNaLice <= 50)
            {
                for (int i = 10; i <= 25; i++)
                    ChangeDurationComboBox.Items.Add(i);
            }
            else
            {
                switch (presmetka.godiniNaLice)
                {
                    case 51:
                        for (int i = 10; i <= 24; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 52:
                        for (int i = 10; i <= 23; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 53:
                        for (int i = 10; i <= 22; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 54:
                        for (int i = 10; i <= 21; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 55:
                        for (int i = 10; i <= 20; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 56:
                        for (int i = 10; i <= 19; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 57:
                        for (int i = 10; i <= 18; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 58:
                        for (int i = 10; i <= 17; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 59:
                        for (int i = 10; i <= 16; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 60:
                        for (int i = 10; i <= 15; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 61:
                        for (int i = 10; i <= 14; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 62:
                        for (int i = 10; i <= 13; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 63:
                        for (int i = 10; i <= 12; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 64:
                        for (int i = 10; i <= 11; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                    case 65:
                        for (int i = 10; i <= 10; i++)
                            ChangeDurationComboBox.Items.Add(i);
                        break;
                }
            }

            kontakt = new ContactModel();
            kontakt.age = presmetka.godiniNaLice;
            kontakt.duration = presmetka.GodiniOsiguruvanje;
            kontakt.premium = presmetka.Premija;
        }

        private void HardwareButtons_BackPressed(object sender, BackPressedEventArgs e)
        {
            Frame rootFrame = Window.Current.Content as Frame;
            if (rootFrame != null && rootFrame.CanGoBack)
            {
                rootFrame.GoBack();
                e.Handled = true;
            }
        }

        private async void ChangeDurationComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

            int selectedIndex = ChangeDurationComboBox.SelectedIndex;
            int selectedValue = (int)ChangeDurationComboBox.SelectedValue;
            presmetka.GodiniOsiguruvanje = selectedValue;

            rezultat = await ResultsClient.GetResultsAsync(presmetka.Gender, presmetka.godiniNaLice, presmetka.GodiniOsiguruvanje, presmetka.Premija);
            if (rezultat[1].OsigurenaSuma != 0)
            {
                CroatiaOS.Text = String.Format("{0} €", rezultat[1].OsigurenaSuma.ToString());
            }
            else
            {
                CroatiaOS.Text = "нема понуда";
            }
            if (rezultat[2].OsigurenaSuma != 0)
            {
                UniqaOS.Text = String.Format("{0} €", rezultat[2].OsigurenaSuma.ToString());
            }
            else
            {
                UniqaOS.Text = "нема понуда";
            }
            if (rezultat[3].OsigurenaSuma != 0)
            {
                WinnerOS.Text = String.Format("{0} €", rezultat[3].OsigurenaSuma.ToString());
            }
            else
            {
                WinnerOS.Text = "нема понуда";
            }
            if (rezultat[0].OsigurenaSuma != 0)
            {
                GraweOS.Text = String.Format("{0} €", rezultat[0].OsigurenaSuma.ToString());
            }
            else
            {
                GraweOS.Text = "нема понуда";
            }
        }

        private async void ChangePremiumComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            int selectedIndex1 = ChangePremiumComboBox.SelectedIndex;
            int selectedValue1 = (int)ChangePremiumComboBox.SelectedValue;
            presmetka.Premija = selectedValue1;
           
            rezultat = await ResultsClient.GetResultsAsync(presmetka.Gender, presmetka.godiniNaLice, presmetka.GodiniOsiguruvanje, presmetka.Premija);
            if (rezultat[1].OsigurenaSuma != 0)
            {
                CroatiaOS.Text = String.Format("{0} €", rezultat[1].OsigurenaSuma.ToString());
            }
            else
            {
                CroatiaOS.Text = "нема понуда";
            }
            if (rezultat[2].OsigurenaSuma != 0)
            {
                UniqaOS.Text = String.Format("{0} €", rezultat[2].OsigurenaSuma.ToString());
            }
            else
            {
                UniqaOS.Text = "нема понуда";
            }
            if (rezultat[3].OsigurenaSuma != 0)
            {
                WinnerOS.Text = String.Format("{0} €", rezultat[3].OsigurenaSuma.ToString());
            }
            else
            {
                WinnerOS.Text = "нема понуда";
            }
            if (rezultat[0].OsigurenaSuma != 0)
            {
                GraweOS.Text = String.Format("{0} €", rezultat[0].OsigurenaSuma.ToString());
            }
            else
            {
                GraweOS.Text = "нема понуда";
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

        private void Croatia_Tapped(object sender, TappedRoutedEventArgs e)
        {
            string croatia = "croatia";
            kontakt.company = croatia;
            kontakt.sum = rezultat[1].OsigurenaSuma;
            Frame.Navigate(typeof(Contact), kontakt);
        }

        private void Uniqa_Tapped(object sender, TappedRoutedEventArgs e)
        {
            string uniqa = "uniqa";
            kontakt.company = uniqa;
            kontakt.sum = rezultat[2].OsigurenaSuma;
            Frame.Navigate(typeof(Contact), kontakt);
        }

        private void Winner_Tapped(object sender, TappedRoutedEventArgs e)
        {
            string winner = "winner";
            kontakt.company = winner;
            kontakt.sum = rezultat[3].OsigurenaSuma;
            Frame.Navigate(typeof(Contact), kontakt);
        }

        private void Grawe_Tapped(object sender, TappedRoutedEventArgs e)
        {
            string grawe = "grawe";
            kontakt.company = grawe;
            kontakt.sum = rezultat[0].OsigurenaSuma;
            Frame.Navigate(typeof(Contact), kontakt);
        }
    }
}
