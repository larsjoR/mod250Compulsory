using System.Windows;
using Display.Client;
using Display.Model;

namespace Display
{

    public partial class MainWindow : Window
    {
        // View-Models 
        TemperatureViewModel TempViewModel = new TemperatureViewModel();
        MotionViewModel MotionViewModel = new MotionViewModel();

        public MainWindow()
        {
            InitializeComponent();
            const string motionIP = "localhost";
            const string tempIP = "localhost";

            // Create instances of the Clients, pass the IP address of the devices
            TemperatureClient tc = new TemperatureClient(TempViewModel,tempIP);
            MotionClient mc = new MotionClient(MotionViewModel,motionIP);
            
            // Start subscription to the motion sensor
            mc.Subscribe();
            DataContext = TempViewModel;

            // Start/stop subscribing whenever a motion event is raised
            MotionViewModel.PropertyChanged += (o, a) =>
            {
                    Dispatcher.Invoke(() => {
                    if (MotionViewModel.Motion) {
                            //Status.Text = "Subscription Started!";
                            boxOverlay.Visibility = Visibility.Collapsed;
                            tc.Subscribe();
                        }
                    else
                        {
                            tc.Unsubscribe();
                            //Status.Text = "Subscription stopped!";
                            boxOverlay.Visibility = Visibility.Visible;
                        }
                });
             
            };

        }


       
        

    

}
}
