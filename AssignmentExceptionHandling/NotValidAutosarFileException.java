

public class NotValidAutosarFileException extends Exception {
		private String message;
		NotValidAutosarFileException (String message)
		{
			super(message);
			this.message=message;
		}
		

}
