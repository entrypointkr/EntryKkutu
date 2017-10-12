package kr.rvs.kkutu.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import kr.rvs.kkutu.network.packet.Packet;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class PacketTypeAdaptor extends TypeAdapter<Packet> {
    private final TypeAdapter<Packet> delegate;

    public PacketTypeAdaptor(TypeAdapter<Packet> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void write(JsonWriter out, Packet value) throws IOException {
        delegate.write(new EndObjectSkippingJsonWriter(out), value);
        out.name("type").value(value.type().internalName());
        out.endObject();
    }

    @Override
    public Packet read(JsonReader in) throws IOException {
        return delegate.read(in);
    }

    class EndObjectSkippingJsonWriter extends JsonWriter {
        private final JsonWriter delegate;

        public EndObjectSkippingJsonWriter(JsonWriter delegate) {
            super(new Writer() {
                @Override
                public void write(char[] cbuf, int off, int len) throws IOException {

                }

                @Override
                public void flush() throws IOException {

                }

                @Override
                public void close() throws IOException {

                }
            });
            this.delegate = delegate;
        }

        @Override
        public boolean isLenient() {
            return delegate.isLenient();
        }

        @Override
        public JsonWriter beginArray() throws IOException {
            return delegate.beginArray();
        }

        @Override
        public JsonWriter endArray() throws IOException {
            return delegate.endArray();
        }

        @Override
        public JsonWriter beginObject() throws IOException {
            return delegate.beginObject();
        }

        @Override
        public JsonWriter endObject() throws IOException {
            return null;
        }

        @Override
        public JsonWriter name(String name) throws IOException {
            return delegate.name(name);
        }

        @Override
        public JsonWriter value(String value) throws IOException {
            return delegate.value(value);
        }

        @Override
        public JsonWriter jsonValue(String value) throws IOException {
            return delegate.jsonValue(value);
        }

        @Override
        public JsonWriter nullValue() throws IOException {
            return delegate.nullValue();
        }

        @Override
        public JsonWriter value(boolean value) throws IOException {
            return delegate.value(value);
        }

        @Override
        public JsonWriter value(Boolean value) throws IOException {
            return delegate.value(value);
        }

        @Override
        public JsonWriter value(double value) throws IOException {
            return delegate.value(value);
        }

        @Override
        public JsonWriter value(long value) throws IOException {
            return delegate.value(value);
        }

        @Override
        public JsonWriter value(Number value) throws IOException {
            return delegate.value(value);
        }

        @Override
        public void flush() throws IOException {
            delegate.flush();
        }

        @Override
        public void close() throws IOException {
            delegate.close();
        }
    }
}
