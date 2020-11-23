import org.apache.hadoop.conf.Configuration;

public class Test {
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.addResource("configuration1.xml");
		/*
		assertThat(conf.get("color"), is("yellow"));
		assertThat(conf.getInt("size", 0), is(10));
		assertThat(conf.get("breadth", "wide"), is("wide"));
		*/
		assertThat(conf.get("color"), "yellow");
		assertThat(conf.getInt("size", 0), 10);
		assertThat(conf.get("breadth", "wide"), "wide");


		Configuration conf2 = new Configuration();
		conf2.addResource("configuration1.xml");
		conf2.addResource("configuration2.xml");

		/*
		assertThat(conf2.getInt("size", 0), is(12));
		assertThat(conf2.get("weight"), is("heavy"));
		*/
		assertThat(conf2.getInt("size", 0), 12);
		assertThat(conf2.get("weight"), "heavy");

		assertThat(conf2.get("size-weight"), ("12, heavy"));
		System.setProperty("size", "14");
		assertThat(conf2.get("size-weight"), ("14, heavy"));

		System.setProperty("length", "2");
		assertThat(conf2.get("length"), (String) null);
	}
}

