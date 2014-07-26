Crie um método que recebe uma lista e a transforma em uma
outra lista de acordo com uma função que diz se o elemento
deve continuar presente ou não.

Exemplo em Java:

    interface Filtro<T> {
        boolean deveManter(T elemento);
    }
    
    class A {
    
        public List<String> filtra(List<String> lista, Filtro<String> filtro){
            // código
        }
    
    }
