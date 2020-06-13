import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dataLayer.crud.Query;
import dataLayer.crud.filters.Eq;
import dataLayer.readers.Reader;

import dataLayer.crud.Entity;

import java.io.IOException;
import java.util.*;

import static dataLayer.crud.Query.create;
import static dataLayer.crud.Query.read;
import static dataLayer.crud.filters.Eq.eq;

public class Main
{
	public static void main(String... args) throws IOException
	{
		Reader.loadConfAndSchema("src/main/resources/configs/configuration.json", "src/main/resources/schemas/schema.json");

		try (MongoClient mongoClient = MongoClients.create())
		{
			mongoClient.getDatabase("TestDB").drop();
			mongoClient.getDatabase("myDB").drop();
		}

		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar.set(1999,Calendar.MAY,16);
		Entity movie = Entity.of("Movie", new HashMap<String, Object>(Map.of("ID", "111111",
				"releaseDate", calendar.getTimeInMillis(),
				"title", "Star Wars: Episode I – The Phantom Menace",
				"avgRating", 4.5,
				"length", 210,
				"media", "Blu-Ray",
				"income", 10000000)));

		calendar.set(1944, Calendar.MAY,14);
		calendar2.set(2020,Calendar.OCTOBER,10);
		Entity director = Entity.of("User", new HashMap<String, Object>(Map.of("username", "curiousGeorge",
				"password", "lucasFilms123",
				"email", "george@gmail.com",
				"lastLogin", calendar2.getTimeInMillis(),
				"ID", 121212,
				"name", "George Lucas",
				"dateofbirth", calendar.getTimeInMillis(),
				"gender", true,
				"moviesDirector", Set.of(movie))));

		calendar.set(1954, Calendar.AUGUST,22);
		calendar2.set(2020,Calendar.OCTOBER,10);
		Entity producer = Entity.of("User", new HashMap<String, Object>(Map.of("username", "richieRich",
				"password", "richard789",
				"email", "richard@gmail.com",
				"lastLogin", calendar2.getTimeInMillis(),
				"ID", 131313,
				"name", "Richard McCallum",
				"dateofbirth", calendar.getTimeInMillis(),
				"gender", true,
				"moviesProducer", Set.of(movie))));

		calendar.set(1989, Calendar.MARCH,5);
		calendar2.set(2020,Calendar.OCTOBER,10);
		Entity actor1 = Entity.of("User", new HashMap<String, Object>(Map.of("username", "jakeWalker",
				"password", "jakeJ231",
				"email", "jake@gmail.com",
				"lastLogin", calendar2.getTimeInMillis(),
				"ID", 141414,
				"name", "Jake Lloyd",
				"dateofbirth", calendar.getTimeInMillis(),
				"gender", true,
				"moviesActor", Set.of(movie))));

		calendar.set(1944, Calendar.MAY,25);
		calendar2.set(2020,Calendar.OCTOBER,10);
		Entity actor2 = Entity.of("User", new HashMap<String, Object>(Map.of("username", "wizOz",
				"password", "wizardOz212",
				"email", "frank@gmail.com",
				"lastLogin", calendar2.getTimeInMillis(),
				"ID", 151515,
				"name", "Frank Oz",
				"dateofbirth", calendar.getTimeInMillis(),
				"gender", true,
				"moviesActor", Set.of(movie))));

		calendar.set(1944, Calendar.AUGUST,11);
		calendar2.set(2020,Calendar.OCTOBER,10);
		Entity actor3 = Entity.of("User", new HashMap<String, Object>(Map.of("username", "mcDiarmid",
				"password", "ianMac547",
				"email", "ian@gmail.com",
				"lastLogin", calendar2.getTimeInMillis(),
				"ID", 161616,
				"name", "Ian McDiarmid",
				"dateofbirth", calendar.getTimeInMillis(),
				"gender", true,
				"moviesActor", Set.of(movie))));

		Entity genre1 = Entity.of("Genre", Map.of("name", "Sci-Fi",
			"movies", Set.of(movie)));

		Entity genre2 = Entity.of("Genre", Map.of("name", "Adventure",
				"movies", Set.of(movie)));

		movie.putField("directors", Set.of(director));

		movie.putField("producers", Set.of(producer));

		movie.putField("genres", Set.of(genre1, genre2));

		Entity role1 = Entity.of("MovieRole", Map.of("user", actor1,
				"movie", movie,
				"roles", Set.of("Anakin Skywalker")));

		Entity role2 = Entity.of("MovieRole", Map.of("user", actor2,
				"movie", movie,
				"roles", Set.of("Yoda")));

		Entity role3 = Entity.of("MovieRole", Map.of("user", actor3,
				"movie", movie,
				"roles", Set.of("Sheev Palpatine")));

		create(movie, director, producer, actor1, actor2, actor3, genre1, genre2, role1, role2, role3);

		Entity result = read(eq("User", "ID", 121212)).stream()
				.findFirst()
				.get();

//		System.out.println(result);
		System.out.println(Reader.toJson(result));

//		Date dateofbirth = new Date((Long) result.get("dateofbirth"));
//		System.out.println(dateofbirth);
	}
}
