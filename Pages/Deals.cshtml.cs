using Microsoft.AspNetCore.Mvc.RazorPages;

namespace PizzaHub.Pages
{
    public class DealsModel : PageModel
    {
        public void OnGet()
        {
            string dealsMessage;

            
            var isWeekend = true; 
            if (isWeekend)
            {
                dealsMessage = "Weekend Special: Buy 1 Get 1 Free!";
            }
            else
            {
                dealsMessage = "Weekday Deal: 20% off your order!";
            }

            ViewData["DealsMessage"] = dealsMessage;
        }
    }
}
