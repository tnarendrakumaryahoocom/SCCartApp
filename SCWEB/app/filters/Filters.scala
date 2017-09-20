import javax.inject._
import play.api._
import play.api.http.HttpFilters
import play.api.mvc._

import filters.ScFilter


@Singleton
class Filters @Inject() (
  env: Environment,
  scFilter: ScFilter) extends HttpFilters {

  override val filters = {
    if (env.mode == Mode.Dev) Seq(scFilter) else Seq.empty
  }

}
