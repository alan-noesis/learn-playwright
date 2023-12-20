package wrapper;

import org.json.JSONObject;
import org.noesis.Actions;
import org.noesis.Context;
import org.noesis.Generator;
import org.noesis.Headers;

public class Endpoint {
    private final Actions actions;
    private final Context context;

    public Endpoint(Actions actions, Context context) {
        this.actions = actions;
        this.context = context;
    }

    public void createUser() {
        var headers = new Headers();
        headers.add("content-type", "application/json");
        headers.add("Authorization", "Bearer " + "$token");

        var body = STR."""
            {
                "email": "teste.\{Generator.digits(10)}@teste.com",
                "firstName": "\{Generator.firstName()}",
                "lastName": "\{Generator.lastName()}",
                "password": "\{Generator.uuid()}",
                "phone": "219\{Generator.digits(8)}"
            }
        """;

        try {
            var response = actions.post("http://127.0.0.1:8080/users", headers, body);

            if (response.status() == 201) {
                var json = new JSONObject(response.text());
                context.set("ID", json.getString("id"));
                context.set("FIRST_NAME", json.getString("firstName"));
                context.set("LAST_NAME", json.getString("lastName"));
                context.set("PASSWORD", json.getString("password"));
                actions.log(STR."Criou o usuario: \{json.getString("id")}");
            } else {
                throw new RuntimeException(STR."Falhou ao criar o usuario: \{response.text()}");
            }
        } catch (Exception e) {
            actions.logFail(e.getMessage());
            throw e;
        }
    }

    public void activateUser() {
        var headers = new Headers();
        headers.add("content-type", "application/json");
        headers.add("Authorization", "Bearer " + "$token");

        try {
            var response = actions.patch(STR."http://127.0.0.1:8080/users/activate/\{context.get("ID")}", headers);
            if (response.status() == 200) {
                var json = new JSONObject(response.text());
                context.set("STATUS", json.getString("status"));
                actions.log("Ativou o usuario");
            } else {
                throw new RuntimeException(STR."Falhou ao ativar o usuario: \{response.text()}");
            }
        } catch (Exception e) {
            actions.logFail(e.getMessage());
            throw e;
        }
    }
}
