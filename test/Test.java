import org.apache.hadoop.conf.Configuration;

Configuration conf = new Configuration();
conf.addResource("configuration1.xml");
assertThat(conf.get("color"), is("yellow"));
assertThat(conf.getInt("size", 0), is(10));
assertThat(conf.get("breadth", "wide"), is("wide"));

Configuration conf2 = new Configuration();
conf2.addResource("configuration1.xml");
conf2.addResource("configuration2.xml");

assertThat(conf2.getInt("size", 0), is(12));
assertThat(conf2.get("weight"), is("heavy"));

assertThat(conf2.get("size-weight"), is("12, heavy"));
System.setProperty("size", 14);
assertThat(conf2.get("size-weight"), is("14, heavy"));

System.setProperty("length", "2");
assertThat(conf2.get("length"), is((String) null));