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
public final class EmployeesByDepartmentQuery implements Query<EmployeesByDepartmentQuery.Data, EmployeesByDepartmentQuery.Data, EmployeesByDepartmentQuery.Variables> {
  public static final String OPERATION_DEFINITION = "query EmployeesByDepartment($departmentId: Int!) {\n"
      + "  employeesByDepartment(departmentId: $departmentId) {\n"
      + "    __typename\n"
      + "    id\n"
      + "    name\n"
      + "  }\n"
      + "}";

  public static final String OPERATION_ID = "aed2f7d05a2e1d2b5dc61fb2a9678bd08132b0cef2cc769c6d3c34e090f00da0";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

  public static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "EmployeesByDepartment";
    }
  };

  private final EmployeesByDepartmentQuery.Variables variables;

  public EmployeesByDepartmentQuery(int departmentId) {
    variables = new EmployeesByDepartmentQuery.Variables(departmentId);
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
  public EmployeesByDepartmentQuery.Data wrapData(EmployeesByDepartmentQuery.Data data) {
    return data;
  }

  @Override
  public EmployeesByDepartmentQuery.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<EmployeesByDepartmentQuery.Data> responseFieldMapper() {
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
    private int departmentId;

    Builder() {
    }

    public Builder departmentId(int departmentId) {
      this.departmentId = departmentId;
      return this;
    }

    public EmployeesByDepartmentQuery build() {
      return new EmployeesByDepartmentQuery(departmentId);
    }
  }

  public static final class Variables extends Operation.Variables {
    private final int departmentId;

    private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(int departmentId) {
      this.departmentId = departmentId;
      this.valueMap.put("departmentId", departmentId);
    }

    public int departmentId() {
      return departmentId;
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
          writer.writeInt("departmentId", departmentId);
        }
      };
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forList("employeesByDepartment", "employeesByDepartment", new UnmodifiableMapBuilder<String, Object>(1)
      .put("departmentId", new UnmodifiableMapBuilder<String, Object>(2)
        .put("kind", "Variable")
        .put("variableName", "departmentId")
        .build())
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nullable List<EmployeesByDepartment> employeesByDepartment;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Data(@Nullable List<EmployeesByDepartment> employeesByDepartment) {
      this.employeesByDepartment = employeesByDepartment;
    }

    public @Nullable List<EmployeesByDepartment> employeesByDepartment() {
      return this.employeesByDepartment;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeList($responseFields[0], employeesByDepartment, new ResponseWriter.ListWriter() {
            @Override
            public void write(Object value, ResponseWriter.ListItemWriter listItemWriter) {
              listItemWriter.writeObject(((EmployeesByDepartment) value).marshaller());
            }
          });
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "employeesByDepartment=" + employeesByDepartment
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
        return ((this.employeesByDepartment == null) ? (that.employeesByDepartment == null) : this.employeesByDepartment.equals(that.employeesByDepartment));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (employeesByDepartment == null) ? 0 : employeesByDepartment.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final EmployeesByDepartment.Mapper employeesByDepartmentFieldMapper = new EmployeesByDepartment.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final List<EmployeesByDepartment> employeesByDepartment = reader.readList($responseFields[0], new ResponseReader.ListReader<EmployeesByDepartment>() {
          @Override
          public EmployeesByDepartment read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<EmployeesByDepartment>() {
              @Override
              public EmployeesByDepartment read(ResponseReader reader) {
                return employeesByDepartmentFieldMapper.map(reader);
              }
            });
          }
        });
        return new Data(employeesByDepartment);
      }
    }
  }

  public static class EmployeesByDepartment {
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

    public EmployeesByDepartment(@NotNull String __typename, @NotNull String id,
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
        $toString = "EmployeesByDepartment{"
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
      if (o instanceof EmployeesByDepartment) {
        EmployeesByDepartment that = (EmployeesByDepartment) o;
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

    public static final class Mapper implements ResponseFieldMapper<EmployeesByDepartment> {
      @Override
      public EmployeesByDepartment map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String id = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[1]);
        final String name = reader.readString($responseFields[2]);
        return new EmployeesByDepartment(__typename, id, name);
      }
    }
  }
}
