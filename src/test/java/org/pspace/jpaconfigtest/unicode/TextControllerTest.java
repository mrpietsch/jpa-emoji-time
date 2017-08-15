package org.pspace.jpaconfigtest.unicode;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.pspace.jpaconfigtest.testsupport.VirginDatabaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
public class TextControllerTest extends VirginDatabaseTest {

    private static final String EMOJI = "\uD83D\uDE00";

    @Autowired WebApplicationContext wac;
    @Autowired
    TextRepository eventService;

    private MockMvc       mockMvc;
    private TextEntity textEntity;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        textEntity = new TextEntity();
        textEntity.setName("Name" + EMOJI);
        textEntity.setText("Text" + EMOJI);

        eventService.save(textEntity);
    }

    @Test
    public void get_utf8() throws Exception { doGetUsingCharset("UTF-8"); }

    @Test
    @Ignore("Ingored because of Exception in JSON framework: This is not a json object according to the JsonProvider: 'com.jayway.jsonpath.spi.json.JsonSmartJsonProvider'")
    public void get_utf16() throws Exception { doGetUsingCharset("UTF-16"); }

    @Test
    public void get_utf16LE() throws Exception { doGetUsingCharset("UTF-16LE"); }

    @Test
    public void get_utf16BE() throws Exception { doGetUsingCharset("UTF-16BE"); }

    @Test
    public void post_utf8() throws Exception { doPostUsingCharset("UTF-8"); }

    @Test
    public void post_utf16() throws Exception { doPostUsingCharset("UTF-16"); }

    @Test
    public void post_utf16LE() throws Exception { doPostUsingCharset("UTF-16LE"); }

    @Test
    public void post_utf16BE() throws Exception { doPostUsingCharset("UTF-16BE"); }


    private void doGetUsingCharset(String charsetString) throws Exception {
        String    url               = "/textEntities/" + textEntity.getId();
        Charset   charset           = Charset.forName(charsetString);
        MediaType expectedMediaType = new MediaType("application", "json", charset);

        this.mockMvc
                .perform(get(url).accept(expectedMediaType))
                .andExpect(status().isOk())
                .andExpect(content().encoding(charset.name()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(textEntity.getName())))
                .andExpect(jsonPath("$.text", is(textEntity.getText())))
        ;
    }

    private void doPostUsingCharset(String charsetUnderTest) throws Exception {
        Charset charset = Charset.forName(charsetUnderTest);
        String  content = String.format("Emoji: " + EMOJI + " (%s)", charsetUnderTest);
        String  jsonDoc = "{ \"name\": \"" + content + "\" }";

        String    url       = "/textEntities/update";
        MediaType mediaType = new MediaType("application", "json", charset);

        // convert to bytes according to the specified charset under test
        byte[] contentBytes = jsonDoc.getBytes(charset);

        MockHttpServletRequestBuilder requestBuilder = post(url)
                .contentType(mediaType)
                .content(contentBytes);

        this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is3xxRedirection());
    }
}