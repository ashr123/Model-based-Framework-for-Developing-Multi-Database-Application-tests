import dataLayer.readers.Reader;

import java.io.IOException;

public class Main
{
	public static void main(String... args) throws IOException
	{
		Reader.loadConfAndSchema("src/main/resources/configurationMongoDBTest.json", "src/main/resources/SchemaTest.json");

//		final Entity
//				roy = Entity.of("Person",
//				Map.of("name", "Roy",
//						"age", 27L,
//						"phoneNumber", "0546815181",
//						"emailAddress", "ashr@post.bgu.ac.il")),
//				yossi = Entity.of("Person",
//						Map.of("name", "Yossi",
//								"age", 22L,
//								"phoneNumber", "0587158627",
//								"emailAddress", "yossilan@post.bgu.ac.il")),
//				karin = Entity.of("Person",
//						Map.of("name", "Karin",
//								"age", 26L,
//								"phoneNumber", "0504563434",
//								"emailAddress", "davidz@post.bgu.ac.il"));
//
//		Query.create(roy, yossi, karin);
	}
}
