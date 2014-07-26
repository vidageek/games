Crie um método que recebe uma lista e a transforma em uma
outra lista de acordo com uma função de transformação.

Exemplo em Java:

    interface Transformacao<F, T> {
        T transforma(F original);
    }
    
    class A {
    
        public List<String> transformaLista(List<Int> lista, Transformacao<Int, String> transformacao){
            // código
        }
    
    }
