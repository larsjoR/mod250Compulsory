using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Display.Model
{   
    /// <summary>
    /// The temperature view-model.
    /// Extends the INotifyPropertyChanged to raise event 
    /// whenever the temperature property is changed. 
    /// </summary>
    public class TemperatureViewModel : INotifyPropertyChanged
    {
        private string temperature;

        public string Temperature
        {
            get
            {
                double temp;

                if (Double.TryParse(temperature, NumberStyles.Any, System.Globalization.CultureInfo.InvariantCulture, out temp))
                {
                    return temp.ToString("##.# C");
                }

                return temperature;
            }
            set { temperature = value; OnPropertyChanged("Temperature"); }
        }

        public event PropertyChangedEventHandler PropertyChanged;

        public TemperatureViewModel() { Temperature = "No value detected yet.."; }

        public void OnPropertyChanged(string value)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(value));
        }
        
    }
}
