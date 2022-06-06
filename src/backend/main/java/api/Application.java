package backend.main.java.api;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("") public class Application extends ResourceConfig
{
	public Application()
	{
		super();
		registerClasses(getServiceClasses());
	}

	protected Set<Class<?>> getServiceClasses()
	{
		final Set<Class<?>> services = new HashSet<>();

		services.add(ArticleService.class);
		services.add(CartService.class);
		services.add(ContactService.class);
		services.add(CouponService.class);
		services.add(PictureService.class);
		services.add(OrderService.class);
		services.add(UserService.class);
		services.add(WishlistService.class);
		services.add(AdminService.class);

		return services;
	}
}
