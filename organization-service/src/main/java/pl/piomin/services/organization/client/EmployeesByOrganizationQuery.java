package pl.piomin.services.organization.client;

import com.apollographql.apollo.api.InputFieldMarshaller;
import com.apollographql.apollo.api.InputFieldWriter;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import type.CustomType;

@Generated("Apollo GraphQL")
public final class EmployeesByOrganizationQuery implements Query<EmployeesByOrganizationQuery.Data, EmployeesByOrganizationQuery.Data, EmployeesByOrganizationQuery.Variables> {
  public static final String OPERATION_DEFINITION = "query EmployeesByOrganization($organizationId: Int!) {\n"
      + "  employeesByOrganization(organizationId: $organizationId) {\n"
      + "    __typename\n"
      + "    id\n"
      + "    name\n"
      + "  }\n"
      + "}";

  public static final String OPERATION_ID = "e22dfe6efe53b9e0faa27de63eda414ee6fa8fa9a8d0cc4269ef7bce117705a9";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

  public static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "EmployeesByOrganization";
    }
  };

  private final EmployeesByOrganizationQuery.Variables variables;

  public EmployeesByOrganizationQuery(int organizationId) {
    variables = new EmployeesByOrganizationQuery.Variables(organizationId);
  }

  @Override
  public String operationId() {
    return OPERATION_ID;
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public EmployeesByOrganizationQuery.Data wrapData(EmployeesByOrganizationQuery.Data data) {
    return data;
  }

  @Override
  public EmployeesByOrganizationQuery.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<EmployeesByOrganizationQuery.Data> responseFieldMapper() {
    return new Data.Mapper();
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public OperationName name() {
    return OPERATION_NAME;
  }

  public static final class Builder {
    private int organizationId;

    Builder() {
    }

    public Builder organizationId(int organizationId) {
      this.organizationId = organizationId;
      return this;
    }

    public EmployeesByOrganizationQuery build() {
      return new EmployeesByOrganizationQuery(organizationId);
    }
  }

  public static final class Variables extends Operation.Variables {
    private final int organizationId;

    private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(int organizationId) {
      this.organizationId = organizationId;
      this.valueMap.put("organizationId", organizationId);
    }

    public int organizationId() {
      return organizationId;
    }

    @Override
    public Map<String, Object> valueMap() {
      return Collections.unmodifiableMap(valueMap);
    }

    @Override
    public InputFieldMarshaller marshaller() {
      return new InputFieldMarshaller() {
        @Override
        public void marshal(InputFieldWriter writer) throws IOException {
          writer.writeInt("organizationId", organizationId);
        }
      };
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forList("employeesByOrganization", "employeesByOrganization", new UnmodifiableMapBuilder<String, Object>(1)
      .put("organizationId", new UnmodifiableMapBuilder<String, Object>(2)
        .put("kind", "Variable")
        .put("variableName", "organizationId")
        .build())
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nullable List<EmployeesByOrganization> employeesByOrganization;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Data(@Nullable List<EmployeesByOrganization> employeesByOrganization) {
      this.employeesByOrganization = employeesByOrganization;
    }

    public @Nullable List<EmployeesByOrganization> employeesByOrganization() {
      return this.employeesByOrganization;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeList($responseFields[0], employeesByOrganization, new ResponseWriter.ListWriter() {
            @Override
            public void write(Object value, ResponseWriter.ListItemWriter listItemWriter) {
              listItemWriter.writeObject(((EmployeesByOrganization) value).marshaller());
            }
          });
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "employeesByOrganization=" + employeesByOrganization
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return ((this.employeesByOrganization == null) ? (that.employeesByOrganization == null) : this.employeesByOrganization.equals(that.employeesByOrganization));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (employeesByOrganization == null) ? 0 : employeesByOrganization.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final EmployeesByOrganization.Mapper employeesByOrganizationFieldMapper = new EmployeesByOrganization.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final List<EmployeesByOrganization> employeesByOrganization = reader.readList($responseFields[0], new ResponseReader.ListReader<EmployeesByOrganization>() {
          @Override
          public EmployeesByOrganization read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<EmployeesByOrganization>() {
              @Override
              public EmployeesByOrganization read(ResponseReader reader) {
                return employeesByOrganizationFieldMapper.map(reader);
              }
            });
          }
        });
        return new Data(employeesByOrganization);
      }
    }
  }

  public static class EmployeesByOrganization {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @NotNull String id;

    final @NotNull String name;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public EmployeesByOrganization(@NotNull String __typename, @NotNull String id,
        @NotNull String name) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.id = Utils.checkNotNull(id, "id == null");
      this.name = Utils.checkNotNull(name, "name == null");
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    public @NotNull String id() {
      return this.id;
    }

    public @NotNull String name() {
      return this.name;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeCustom((ResponseField.CustomTypeField) $responseFields[1], id);
          writer.writeString($responseFields[2], name);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "EmployeesByOrganization{"
          + "__typename=" + __typename + ", "
          + "id=" + id + ", "
          + "name=" + name
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof EmployeesByOrganization) {
        EmployeesByOrganization that = (EmployeesByOrganization) o;
        return this.__typename.equals(that.__typename)
         && this.id.equals(that.id)
         && this.name.equals(that.name);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= id.hashCode();
        h *= 1000003;
        h ^= name.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<EmployeesByOrganization> {
      @Override
      public EmployeesByOrganization map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String id = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[1]);
        final String name = reader.readString($responseFields[2]);
        return new EmployeesByOrganization(__typename, id, name);
      }
    }
  }
}
