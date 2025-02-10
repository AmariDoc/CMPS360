using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc;

namespace PizzaHub.Pages
{
    public class LocationsModel : PageModel
    {
        [BindProperty]
        public string City { get; set; }
        public string LocationMessage { get; set; }

        public void OnPost()
        {
            switch (City)
            {
                case "New York":
                    LocationMessage = "Pizza Hub NYC - 123 Main St, New York, NY 10001";
                    break;
                case "Los Angeles":
                    LocationMessage = "Pizza Hub LA - 456 Sunset Blvd, Los Angeles, CA 90001";
                    break;
                case "Chicago":
                    LocationMessage = "Pizza Hub Chicago - 789 Lake Shore Dr, Chicago, IL 60601";
                    break;
                default:
                    LocationMessage = "Please select a city to find a location.";
                    break;
            }
        }
    }
}
