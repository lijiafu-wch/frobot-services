package f.s.frobot.error;

import f.s.jerror.BaseError;
import f.s.jerror.annotation.Errors;

import org.springframework.stereotype.Component;
 /**
  *
  * @author lijiafu
  * @date 2019/9/25 14:49
  */
@Component
@Errors
public interface FrobotErrors {
    BaseError MissingArgument(String arg);
    BaseError ArgumentError(String arg);
    BaseError RequirePositive(String arg);
    BaseError Errors(String str);
    BaseError CustomErroe(String s, String message);
    BaseError IllegalWord();

 }
