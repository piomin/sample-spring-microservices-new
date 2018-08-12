package pl.piomin.services.department.client;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.Utils;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Collections;
import java.util.List;
import javax.annotation.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Generated("Apollo GraphQL")
public final class EmployeesQuery implements Query<EmployeesQuery.Data, EmployeesQuery.Data, Operation.Variables> {
  public static final String OPERATION_DEFINITION = "query EmployeesQuery {\n"
      + "  employees {\n"
      + "    __typename\n"
      + "    name\n"
      + "    age\n"
      + "  }\n"
      + "}";

  public static final String OPERATION_ID = "fb9a898c6d1af441e2a005a73850290ea980e0b0575b833cb06f17dd2331ba13";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

  public static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "EmployeesQuery";
    }
  };

  private final Operation.Variables variables;

  public EmployeesQuery() {
    this.variables = Operation.EMPTY_VARIABLES;
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
  public EmployeesQuery.Data wrapData(EmployeesQuery.Data data) {
    return data;
  }

  @Override
  public Operation.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<EmployeesQuery.Data> responseFieldMapper() {
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
    Builder() {
    }

    public EmployeesQuery build() {
      return new EmployeesQuery();
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forList("employees", "employees", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nullable List<Employee> employees;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Data(@Nullable List<Employee> employees) {
      this.employees = employees;
    }

    public @Nullable List<Employee> employees() {
      return this.employees;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeList($responseFields[0], employees, new ResponseWriter.ListWriter() {
            @Override
            public void write(Object value, ResponseWriter.ListItemWriter listItemWriter) {
              listItemWriter.writeObject(((Employee) value).marshaller());
            }
          });
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "employees=" + employees
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
        return ((this.employees == null) ? (that.employees == null) : this.employees.equals(that.employees));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (employees == null) ? 0 : employees.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final Employee.Mapper employeeFieldMapper = new Employee.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final List<Employee> employees = reader.readList($responseFields[0], new ResponseReader.ListReader<Employee>() {
          @Override
          public Employee read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<Employee>() {
              @Override
              public Employee read(ResponseReader reader) {
                return employeeFieldMapper.map(reader);
              }
            });
          }
        });
        return new Data(employees);
      }
    }
  }

  public static class Employee {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forInt("age", "age", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @NotNull String name;

    final int age;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Employee(@NotNull String __typename, @NotNull String name, int age) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.name = Utils.checkNotNull(name, "name == null");
      this.age = age;
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    public @NotNull String name() {
      return this.name;
    }

    public int age() {
      return this.age;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], name);
          writer.writeInt($responseFields[2], age);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Employee{"
          + "__typename=" + __typename + ", "
          + "name=" + name + ", "
          + "age=" + age
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Employee) {
        Employee that = (Employee) o;
        return this.__typename.equals(that.__typename)
         && this.name.equals(that.name)
         && this.age == that.age;
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
        h ^= name.hashCode();
        h *= 1000003;
        h ^= age;
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Employee> {
      @Override
      public Employee map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String name = reader.readString($responseFields[1]);
        final int age = reader.readInt($responseFields[2]);
        return new Employee(__typename, name, age);
      }
    }
  }
}
