package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.junit.Test;

import com.example.oneway.Destination;
import com.example.oneway.Source;

public class DozerMappingTest {

    private Mapper mapper;
    
    @Test
    public void testClassOneWay() {
        mapper = buildMapper("mappings/classOneWay.xml");

        Source source = new Source();
        source.setName("John");
        source.setId("100");
        source.setAge(17);
        
        // source -> destination
        Destination destination = mapper.map(source, Destination.class);
        assertThat(destination.getUserId(), is(source.getId()));
        assertThat(destination.getAge(), is(source.getAge()));
        assertThat(destination.getName(), is(nullValue()));

        // source <- destination (re)
        destination.setName("Tom");
        Source reversedSource = mapper.map(destination, Source.class);
        assertThat(reversedSource.getId(), is(nullValue()));
        assertThat(reversedSource.getAge(), is(0)); // ver.6.1.0 earlier copied.
        assertThat(reversedSource.getName(), is(nullValue())); // ver.6.1.0 earlier copied.
    }

    @Test
    public void testFieldsOneWay() {
        mapper = buildMapper("mappings/fieldsOneWay.xml");
    
        Source source = new Source();
        source.setName("John");
        source.setId("100");
        source.setAge(17);
        
        // source -> destination
        Destination destination = mapper.map(source, Destination.class);
        assertThat(destination.getUserId(), is(source.getId()));
        assertThat(destination.getAge(), is(source.getAge()));
        assertThat(destination.getName(), is(nullValue()));

        // source <- destination (re)
        destination.setName("Tom");
        Source reversedSource = mapper.map(destination, Source.class);
        assertThat(reversedSource.getId(), is(nullValue()));
        assertThat(reversedSource.getAge(), is(destination.getAge()));
        assertThat(reversedSource.getName(), is(destination.getName()));
    }
    
    @Test
    public void testClassFieldsOneWay() {
        mapper = buildMapper("mappings/classFieldsOneWay.xml");

        Source source = new Source();
        source.setName("John");
        source.setId("100");
        source.setAge(17);
        
        // source -> destination
        Destination destination = mapper.map(source, Destination.class);
        assertThat(destination.getUserId(), is(source.getId()));
        assertThat(destination.getAge(), is(source.getAge()));
        assertThat(destination.getName(), is(nullValue()));

        // source <- destination (re)
        destination.setName("Tom");
        Source reversedSource = mapper.map(destination, Source.class);
        assertThat(reversedSource.getId(), is(nullValue()));
        assertThat(reversedSource.getAge(), is(0)); // ver.6.1.0 earlier copied.
        assertThat(reversedSource.getName(), is(nullValue())); // ver.6.1.0 earlier copied.
    }
    
    private Mapper buildMapper(String mappingFile) {
//        return new DozerBeanMapper(Collections.singletonList(mappingFile)); // ver.6.0.0 earlier.
        return DozerBeanMapperBuilder.create().withMappingFiles(mappingFile).build(); // ver.6.1.0 later.
    }

}
