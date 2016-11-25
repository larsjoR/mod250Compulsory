using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Display.Model
{
    /// <summary>
    /// /// <summary>
    /// The motion detection view-model.
    /// Extends the INotifyPropertyChanged to raise event 
    /// whenever the property is changed. 
    /// </summary>
    /// </summary>
    class MotionViewModel : INotifyPropertyChanged 
    {
        public bool _motion { get; set; }
        public bool Motion { get { return _motion; } set { _motion = value;  OnPropertyChanged("Motion"); } }

        public event PropertyChangedEventHandler PropertyChanged;



        public MotionViewModel() { Motion = false; }
        public void OnPropertyChanged(string name)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
  
        }

        
    }
}
