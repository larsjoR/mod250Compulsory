using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CoAP;
using Display.Model;

namespace Display.Client
{
    public class TemperatureClient
    {

        public TemperatureViewModel _tempViewModel { get; set; }
        public Request _req { get; set; }
        public string _ip { get; set; }

        private byte[] token;
        private System.Net.EndPoint destination;

        public TemperatureClient(TemperatureViewModel tempViewModel, string ip)
        {
            _tempViewModel = tempViewModel;
            _req = new Request(Method.GET, true);
            _ip = ip;
        }


        /// <summary>
        /// Starts the subscription to the temperature service
        /// </summary>
        public void Subscribe()
        {
            _req = new Request(Method.GET,true);
            _req.URI = new Uri("coap://"+_ip+":5683/temperature");
            _req.SetPayload("", MediaType.TextPlain);
            _req.MarkObserve();
            _req.Send();

            _req.Respond += UpdateTemperature;

        }
        
        /// <summary>
        /// Stops the current subscription to the temperature service
        /// </summary>
        public void Unsubscribe()
        {
            if (_req?.Token == null)
            {
                return;
            }

            _req.Respond -= UpdateTemperature;

            Request cancelReq = Request.NewGet();
            cancelReq.SetOptions(_req.GetOptions());
            cancelReq.MarkObserveCancel();
            cancelReq.Token = token;
            cancelReq.Destination = destination;
            cancelReq.Send();
        }

        /// <summary>
        /// The event is fired to update the current temperature in the 
        /// view-model. 
        /// </summary>
        /// <param name="sender">The sender object</param>
        /// <param name="args">The event arguments containing the payload</param>
        public void UpdateTemperature(object sender, ResponseEventArgs args)
        {
            destination = _req.Destination;
            token = args.Response.Token;
            _tempViewModel.Temperature = args.Response.PayloadString;

        }
    }
}
