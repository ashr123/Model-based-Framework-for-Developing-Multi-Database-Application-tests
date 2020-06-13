import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dataLayer.crud.Entity;
import dataLayer.readers.Reader;

import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

import static dataLayer.crud.Query.create;
import static dataLayer.crud.Query.read;
import static dataLayer.crud.filters.Eq.eq;

public class Main
{
	public static void main(String... args) throws IOException
	{
		Reader.loadConfAndSchema("src/main/resources/configs/configuration.json",
				"src/main/resources/schemas/schema.json",
				true);

		try (MongoClient mongoClient = MongoClients.create())
		{
			mongoClient.getDatabase("TestDB").drop();
			mongoClient.getDatabase("myDB").drop();
		}

		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar.set(1999, Calendar.MAY, 16);
		Entity movie = Entity.of("Movie")
				.putField("ID", "111111")
				.putField("releaseDate", calendar.getTimeInMillis())
				.putField("title", "Star Wars: Episode I – The Phantom Menace")
				.putField("avgRating", 4.5)
				.putField("length", 210)
				.putField("media", "Blu-Ray")
				.putField("income", 10000000);

		calendar.set(1944, Calendar.MAY, 14);
		calendar2.set(2020, Calendar.OCTOBER, 10);
		Entity director = Entity.of("User")
				.putField("username", "curiousGeorge")
				.putField("password", "lucasFilms123")
				.putField("email", "george@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 121212)
				.putField("name", "George Lucas")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("moviesDirector", Set.of(movie));

		calendar.set(1954, Calendar.AUGUST, 22);
		calendar2.set(2020, Calendar.OCTOBER, 10);
		Entity producer = Entity.of("User")
				.putField("username", "richieRich")
				.putField("password", "richard789")
				.putField("email", "richard@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 131313)
				.putField("name", "Richard McCallum")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("moviesProducer", Set.of(movie));

		calendar.set(1989, Calendar.MARCH, 5);
		calendar2.set(2020, Calendar.OCTOBER, 10);
		Entity actor1 = Entity.of("User")
				.putField("username", "jakeWalker")
				.putField("password", "jakeJ231")
				.putField("email", "jake@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 141414)
				.putField("name", "Jake Lloyd")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("moviesActor", Set.of(movie));

		calendar.set(1944, Calendar.MAY, 25);
		calendar2.set(2020, Calendar.OCTOBER, 10);
		Entity actor2 = Entity.of("User")
				.putField("username", "wizOz")
				.putField("password", "wizardOz212")
				.putField("email", "frank@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 151515)
				.putField("name", "Frank Oz")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("moviesActor", Set.of(movie));

		calendar.set(1944, Calendar.AUGUST, 11);
		calendar2.set(2020, Calendar.OCTOBER, 10);
		Entity actor3 = Entity.of("User")
				.putField("username", "mcDiarmid")
				.putField("password", "ianMac547")
				.putField("email", "ian@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 161616)
				.putField("name", "Ian McDiarmid")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("moviesActor", Set.of(movie));

		Entity genre1 = Entity.of("Genre")
				.putField("name", "Sci-Fi")
				.putField("movies", Set.of(movie));

		Entity genre2 = Entity.of("Genre")
				.putField("name", "Adventure")
				.putField("movies", Set.of(movie));

		movie.putField("directors", Set.of(director));

		movie.putField("producers", Set.of(producer));

		movie.putField("genres", Set.of(genre1, genre2));

		Entity role1 = Entity.of("MovieRole")
				.putField("user", actor1)
				.putField("movie", movie)
				.putField("roles", Set.of("Anakin Skywalker"));

		Entity role2 = Entity.of("MovieRole")
				.putField("user", actor2)
				.putField("movie", movie)
				.putField("roles", Set.of("Yoda"));

		Entity role3 = Entity.of("MovieRole")
				.putField("user", actor3)
				.putField("movie", movie)
				.putField("roles", Set.of("Sheev Palpatine"));

		create(movie, director, producer, actor1, actor2, actor3, genre1, genre2, role1, role2, role3);

		//noinspection OptionalGetWithoutIsPresent
		Entity result = read(eq("User", "ID", 121212)).stream()
				.findFirst()
				.get();

//		System.out.println(result);
		System.out.println(Reader.toJson(result));

//		Date dateofbirth = new Date((Long) result.get("dateofbirth"));
//		System.out.println(dateofbirth);
	}
}
