package oy.tol.tra;


public class ParenthesisChecker {

    private ParenthesisChecker() {
    }


    public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {

        int i=0;

        for(char characters : fromString.toCharArray()){

            if(characters=='{'
                    ||characters=='['
                    ||characters=='(')
            {
                stack.push(characters);
                i++;
            }

            if(characters=='}'
                    ||characters==']'
                    ||characters==')')
            {
                if(stack.isEmpty())
                    throw new ParenthesesException("Invalid parenthesis in file", ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
                else{
                    var ss=stack.pop();
                    if((ss.equals('{')&&characters=='}')
                            ||(ss.equals('[')&&characters==']')
                            ||(ss.equals('(')&&characters==')')){
                        i++;
                    }
                    else
                        throw new ParenthesesException("Invalid parenthesis order", ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
                }


            }


        }

        boolean value=stack.isEmpty();

        if(value==false){
            throw new ParenthesesException("need more Parentheses", ParenthesesException.TOO_FEW_CLOSING_PARENTHESES);
        }
        else
            return i ;



    }







}
