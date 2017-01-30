using LifeInsuranceAdvisor.Classes;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Text;
using System.Threading.Tasks;
using Windows.ApplicationModel.Email;
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
    public sealed partial class Contact : Page
    {
        ContactModel contact;

        public Contact()
        {
            this.InitializeComponent();
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            HardwareButtons.BackPressed += HardwareButtons_BackPressed;

            contact = (ContactModel)e.Parameter;
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

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {
            Frame.GoBack();
        }

        private async void SendButton_Click(object sender, RoutedEventArgs e)
        {
            EmailRecipient sendTo = new EmailRecipient()
            {
                Address = String.Format("{0}@live.com", contact.company)
            };

            EmailMessage mail = new EmailMessage();
            mail.Subject = "Апликација за понудата";
            mail.Body = String.Format("До {0} {1}{2} {3}{18} {4} Возраст: {5}{6} Период на осигурување: {7}{8} Годишна премија: {9}{10} Осигурена сума: {11} €{12}{13} Име: {14}{15} Телефон: {16}{17}",
                contact.company.ToUpper(), Environment.NewLine, Environment.NewLine,
                MessageTextBox.Text.ToString(), Environment.NewLine, contact.age, Environment.NewLine , contact.duration, Environment.NewLine,
            contact.premium, Environment.NewLine, contact.sum, Environment.NewLine, Environment.NewLine, NameTextBox.Text.ToString(), Environment.NewLine,
            TelephoneTextBox.Text.ToString(), Environment.NewLine, Environment.NewLine);

            mail.To.Add(sendTo);

            await EmailManager.ShowComposeNewEmailAsync(mail);
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

    }
}
