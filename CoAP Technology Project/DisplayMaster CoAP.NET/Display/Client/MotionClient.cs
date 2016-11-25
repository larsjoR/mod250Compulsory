using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CoAP;
using Display.Model;

namespace Display.Client
{
    class MotionClient
    {
        public MotionViewModel _motionViewModel { get; set; }
        public Request _req { get; set; }
        public string _ip { get; set; }
        private byte[] token;
        private System.Net.EndPoint destination;

        public MotionClient(MotionViewModel motionViewModel, string ip)
        {
            _motionViewModel = motionViewModel;
            _req = new Request(Method.GET, false);
            _ip = ip;
        }

        /// <summary>
        /// Starts the subscription to the motion detection service
        /// </summary>
        public void Subscribe()
        {

            _req.URI = new Uri("coap://"+_ip+":5683/motion");
            _req.SetPayload("", MediaType.TextPlain);
            _req.MarkObserve();
            _req.Send();
            _req.Respond += UpdateTemperature;

        }

        /// <summary>
        /// Stops the current subscription to the motion detection service
        /// </summary>
        public void Unsubscribe()
        {
            _req.Respond -= UpdateTemperature;

            Request cancelReq = Request.NewGet();
            cancelReq.SetOptions(_req.GetOptions());
            cancelReq.MarkObserveCancel();
            cancelReq.Token = token;
            cancelReq.Destination = destination;
            cancelReq.Send();
        }

        /// <summary>
        /// The event is fired to update the current motion detection status  
        /// of the view-model. 
        /// </summary>
        /// <param name="sender">The sender object</param>
        /// <param name="args">The event arguments containing the payload</param>
        public void UpdateTemperature(object sender, ResponseEventArgs args)
        {
            _motionViewModel.Motion = args.Response.PayloadString.Contains("true") ? true : false;

        }

    }
}
