using Microsoft.AspNetCore.Mvc.RazorPages;

namespace PizzaHub.Pages
{
    public class MenuModel : PageModel
    {
        public void OnGet()
        {
            string menuMessage;

            
            var pizzaType = "Pepperoni"; 
            if (pizzaType == "Pepperoni")
            {
                menuMessage = "Pepperoni Pizza - A classic choice!";
            }
            else if (pizzaType == "Margherita")
            {
                menuMessage = "Margherita Pizza - Fresh and tasty!";
            }
            else
            {
                menuMessage = "Choose a delicious pizza from our menu!";
            }

            ViewData["MenuMessage"] = menuMessage;
        }
    }
}
