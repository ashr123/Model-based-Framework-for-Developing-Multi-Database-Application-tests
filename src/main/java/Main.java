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

		Entity series = Entity.of("Series")
				.putField("ID", "9999")
				.putField("releaseDate", calendar.getTimeInMillis())
				.putField("title", "How I Met Your Mother")
				.putField("avgRating", 5.0)
				.putField("seasons", 8)
				.putField("network", "Don't know");

		Entity episode1 = Entity.of("Episode")
				.putField("ID", "9999")
				.putField("releaseDate", calendar.getTimeInMillis())
				.putField("title", "How I Met Your Mother")
				.putField("avgRating", 5.0)
				.putField("length", 45)
				.putField("season", 1)
				.putField("number", 1)
				.putField("series", Set.of(series));

		Entity episode2 = Entity.of("Episode")
				.putField("ID", "9999")
				.putField("releaseDate", calendar.getTimeInMillis())
				.putField("title", "How I Met Your Mother")
				.putField("avgRating", 5.0)
				.putField("length", 45)
				.putField("season", 1)
				.putField("number", 2)
				.putField("series", Set.of(series));

		Entity episode3 = Entity.of("Episode")
				.putField("ID", "9999")
				.putField("releaseDate", calendar.getTimeInMillis())
				.putField("title", "How I Met Your Mother")
				.putField("avgRating", 5.0)
				.putField("length", 45)
				.putField("season", 1)
				.putField("number", 3)
				.putField("series", Set.of(series));

		Entity episode4 = Entity.of("Episode")
				.putField("ID", "9999")
				.putField("releaseDate", calendar.getTimeInMillis())
				.putField("title", "How I Met Your Mother")
				.putField("avgRating", 5.0)
				.putField("length", 45)
				.putField("season", 1)
				.putField("number", 4)
				.putField("series", Set.of(series));

		Entity episode5 = Entity.of("Episode")
				.putField("ID", "9999")
				.putField("releaseDate", calendar.getTimeInMillis())
				.putField("title", "How I Met Your Mother")
				.putField("avgRating", 5.0)
				.putField("length", 45)
				.putField("season", 1)
				.putField("number", 5)
				.putField("series", Set.of(series));

		Set<Entity> episodes = Set.of(episode1, episode2, episode3, episode4, episode5);

		series.putField("episodes", episodes);

		calendar.set(1945, Calendar.MAY, 14);
		calendar2.set(2000, Calendar.OCTOBER, 10);
		Entity director1 = Entity.of("User")
				.putField("username", "director1")
				.putField("password", "director123")
				.putField("email", "director1@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 10)
				.putField("name", "director1")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("seriesDirector", Set.of(series))
				.putField("episodesDirector", episodes);

		calendar.set(1946, Calendar.MAY, 14);
		calendar2.set(2001, Calendar.OCTOBER, 10);
		Entity director2 = Entity.of("User")
				.putField("username", "director2")
				.putField("password", "director234")
				.putField("email", "director2@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 11)
				.putField("name", "director2")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("seriesDirector", Set.of(series))
				.putField("episodesDirector", episodes);

		calendar.set(1947, Calendar.MAY, 14);
		calendar2.set(2002, Calendar.OCTOBER, 10);
		Entity director3 = Entity.of("User")
				.putField("username", "director3")
				.putField("password", "director345")
				.putField("email", "director3@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 12)
				.putField("name", "director3")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("seriesDirector", Set.of(series))
				.putField("episodesDirector", episodes);

		calendar.set(1948, Calendar.MAY, 14);
		calendar2.set(2003, Calendar.OCTOBER, 10);
		Entity producer1 = Entity.of("User")
				.putField("username", "producer1")
				.putField("password", "producer123")
				.putField("email", "producer1@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 13)
				.putField("name", "producer1")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("seriesProducer", Set.of(series))
				.putField("episodesProducer", episodes);

		calendar.set(1955, Calendar.AUGUST, 22);
		calendar2.set(2004, Calendar.OCTOBER, 10);
		Entity producer2 = Entity.of("User")
				.putField("username", "producer2")
				.putField("password", "producer234")
				.putField("email", "producer2@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 14)
				.putField("name", "producer2")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("seriesProducer", Set.of(series))
				.putField("episodesProducer", episodes);

		calendar.set(1956, Calendar.AUGUST, 22);
		calendar2.set(2005, Calendar.OCTOBER, 10);
		Entity producer3 = Entity.of("User")
				.putField("username", "producer3")
				.putField("password", "producer345")
				.putField("email", "producer3@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 15)
				.putField("name", "producer3")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("seriesProducer", Set.of(series))
				.putField("episodesProducer", episodes);

		calendar.set(1947, Calendar.MAY, 25);
		calendar2.set(2006, Calendar.OCTOBER, 10);
		Entity actor4 = Entity.of("User")
				.putField("username", "actor4")
				.putField("password", "actor456")
				.putField("email", "actor4@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 16)
				.putField("name", "actor4")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("seriesActor", Set.of(series))
				.putField("episodesActor", episodes);

		calendar.set(1948, Calendar.MAY, 25);
		calendar2.set(2007, Calendar.OCTOBER, 10);
		Entity actor5 = Entity.of("User")
				.putField("username", "actor5")
				.putField("password", "actor567")
				.putField("email", "actor5@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 17)
				.putField("name", "actor5")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("seriesActor", Set.of(series))
				.putField("episodesActor", episodes);

		calendar.set(1949, Calendar.MAY, 25);
		calendar2.set(2008, Calendar.OCTOBER, 10);
		Entity actor6 = Entity.of("User")
				.putField("username", "actor6")
				.putField("password", "actor678")
				.putField("email", "actor6@gmail.com")
				.putField("lastLogin", calendar2.getTimeInMillis())
				.putField("ID", 18)
				.putField("name", "actor6")
				.putField("dateofbirth", calendar.getTimeInMillis())
				.putField("gender", true)
				.putField("seriesActor", Set.of(series))
				.putField("episodesActor", episodes);

		Set<Entity> directors = Set.of(director1, director2, director3);
		Set<Entity> producers = Set.of(producer1, producer2, producer3);

		series.putField("directors", directors)
			.putField("producers",producers);

		episode1.putField("directors",directors)
				.putField("producers",producers);

		episode2.putField("directors",directors)
				.putField("producers",producers);

		episode3.putField("directors",directors)
				.putField("producers",producers);

		episode4.putField("directors",directors)
				.putField("producers",producers);

		episode5.putField("directors",directors)
				.putField("producers",producers);

		Entity genre3 = Entity.of("Genre")
				.putField("name", "genre3")
				.putField("series", Set.of(series))
				.putField("episodes", Set.of(episode1, episode2, episode3, episode4, episode5));

		Entity genre4 = Entity.of("Genre")
				.putField("name", "genre4")
				.putField("series", Set.of(series))
				.putField("episodes", Set.of(episode1, episode2, episode3, episode4, episode5));

		Entity genre5 = Entity.of("Genre")
				.putField("name", "genre5")
				.putField("series", Set.of(series))
				.putField("episodes", Set.of(episode1, episode2, episode3, episode4, episode5));

		Set<Entity> genres = Set.of(genre3, genre4, genre5);
		episode1.putField("genres", genres);
		episode2.putField("genres", genres);
		episode3.putField("genres", genres);
		episode4.putField("genres", genres);
		episode5.putField("genres", genres);
		series.putField("genres", genres);

		Entity actorSeriesRole4 = Entity.of("SeriesRole")
				.putField("user", actor4)
				.putField("series", series)
				.putField("roles", Set.of("Actor"));

		Entity actorSeriesRole5 = Entity.of("SeriesRole")
				.putField("user", actor5)
				.putField("series", series)
				.putField("roles", Set.of("Actor"));;

		Entity actorSeriesRole6 = Entity.of("SeriesRole")
				.putField("user", actor6)
				.putField("series", series)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole41 = Entity.of("EpisodeRole")
				.putField("user", actor4)
				.putField("episode", episode1)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole51 = Entity.of("EpisodeRole")
				.putField("user", actor5)
				.putField("episode", episode1)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole61 = Entity.of("EpisodeRole")
				.putField("user", actor6)
				.putField("episode", episode1)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole42 = Entity.of("EpisodeRole")
				.putField("user", actor4)
				.putField("episode", episode2)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole52 = Entity.of("EpisodeRole")
				.putField("user", actor5)
				.putField("episode", episode2)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole62 = Entity.of("EpisodeRole")
				.putField("user", actor6)
				.putField("episode", episode2)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole43 = Entity.of("EpisodeRole")
				.putField("user", actor4)
				.putField("episode", episode3)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole53 = Entity.of("EpisodeRole")
				.putField("user", actor5)
				.putField("episode", episode3)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole63 = Entity.of("EpisodeRole")
				.putField("user", actor6)
				.putField("episode", episode3)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole44 = Entity.of("EpisodeRole")
				.putField("user", actor4)
				.putField("episode", episode4)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole54 = Entity.of("EpisodeRole")
				.putField("user", actor5)
				.putField("episode", episode4)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole64 = Entity.of("EpisodeRole")
				.putField("user", actor6)
				.putField("episode", episode4)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole45 = Entity.of("EpisodeRole")
				.putField("user", actor4)
				.putField("episode", episode5)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole55 = Entity.of("EpisodeRole")
				.putField("user", actor5)
				.putField("episode", episode5)
				.putField("roles", Set.of("Actor"));

		Entity actorEpisodeRole65 = Entity.of("EpisodeRole")
				.putField("user", actor6)
				.putField("episode", episode5)
				.putField("roles", Set.of("Actor"));

		create(series);
		create(episodes);
		create(directors);
		create(producers);
		create(actor4, actor5, actor6);
		create(genres);
		create(actorSeriesRole4, actorSeriesRole5, actorSeriesRole6,
				actorEpisodeRole41,
				actorEpisodeRole42,
				actorEpisodeRole43,
				actorEpisodeRole44,
				actorEpisodeRole45,
				actorEpisodeRole51,
				actorEpisodeRole52,
				actorEpisodeRole53,
				actorEpisodeRole54,
				actorEpisodeRole55,
				actorEpisodeRole61,
				actorEpisodeRole62,
				actorEpisodeRole63,
				actorEpisodeRole64,
				actorEpisodeRole65);

		//		System.out.println(result);
		System.out.println(Reader.toJson(result));

//		Date dateofbirth = new Date((Long) result.get("dateofbirth"));
//		System.out.println(dateofbirth);
	}
}
