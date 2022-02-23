import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean primerLinea = true;
            String file = "";

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (primerLinea) {
                    file = inputLine.split(" ")[1];
                    System.out.println("File" + file);
                    primerLinea = false;
                }
                if (!in.ready()) {
                    break;
                }
            }
            if (file.startsWith("/Clima")) {
                Prueba prueba = new Prueba();

                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>Clima</title>\n"
                        + "</head>"
                        + "<body>"
                        + "\n"
                        + "Type\tDescription\n"
                        + "<input type=\"text\" name=\"coun\">\n"
                        + "<button class=\"button\" onclick=\"inputValues()\">Result</button>"
                        + "<div id=\"resultt\">\n" +
                        "                <p class=\"example\" ></p>\n" +
                        "            </div>"
                        + "<script>"
                        + "let numberc;\n" +
                        "let res1;\n" +
                        "let res2;\n" +
                        "let res = \"\";"
                        + "function inputValues() {\n" +
                        "    numberc = document.getElementsByName(\"coun\")[0].value;\n" +
                        "    const url = \"api.openweathermap.org/data/2.5/find?q=\"+numberc+\"&appid=6d5b39516045f1a21d068ac4cbe0600b\";\n"
                        + "    getapi(url);\n" +
                        "}"
                        + "async function getapi (url) {"
                        + "res1=\"\";"
                        + "alert(url);"
                        + "try {\n" +
                        "            const response = await fetch(url, {method: 'GET', headers: {'Content-Type': 'application/json'}});\n" +
                        "            let data = await response.json();" +
                        "res1 = data;" +
                        "\n" +
                        "        }catch (e) {\n" +
                        "            console.log(e);\n" +
                        "        }\n" +
                        "    var x = document.getElementById(\"resultt\");\n"
                        + "x.querySelector(\".example\").innerHTML = (res1);"
                        + "}\n"
                        + "</script>\n"
                        + "</body>\n"
                        + "</html>\n";
            }else if(file.startsWith("/Consulta?ciudad=London")) {
                System.out.println("siuuuuuuuuuuuuuuuuuu");
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>Clima</title>\n"
                        + "</head>"
                        + "<body>"
                        + "cONSULTA LONDRESSSS\n"
                        + "</body>"
                        + "</html>";
            }else {

                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>Title of the document</title>\n"
                        + "</head>"
                        + "<body>"
                        + "My Web Site"
                        + "</body>"
                        + "</html>";
            }
            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }
}
