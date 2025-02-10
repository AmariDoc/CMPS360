using Microsoft.AspNetCore.Mvc.RazorPages;
using System;

namespace PizzaHub.Pages
{
    public class ContactModel : PageModel
    {
        public string ContactMessage { get; set; }

        public void OnGet()
        {
            int hour = DateTime.Now.Hour;

            if (hour >= 9 && hour <= 17)
            {
                ContactMessage = "We're open! Call us at (123) 456-7890 or visit our store.";
            }
            else
            {
                ContactMessage = "We're currently closed. Please email us at support@pizzahub.com.";
            }
        }
    }
}
