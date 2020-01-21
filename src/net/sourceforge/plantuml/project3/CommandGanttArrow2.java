/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 *
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 * 
 *
 */
package net.sourceforge.plantuml.project3;

import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;

public class CommandGanttArrow2 extends SingleLineCommand2<GanttDiagram> {

	public CommandGanttArrow2() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandGanttArrow2.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("TASK1", "\\[([^\\[\\]]+?)\\]"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("ARROW", "(-+)\\>"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("TASK2", "\\[([^\\[\\]]+?)\\]"), //
				RegexLeaf.spaceZeroOrMore(), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg) {

		final String name1 = arg.get("TASK1", 0);
		final String name2 = arg.get("TASK2", 0);
		final Task task1 = diagram.getOrCreateTask(name1, null, false);
		final Task task2 = diagram.getOrCreateTask(name2, null, false);

		diagram.setTaskOrder(task1, task2);

		return CommandExecutionResult.ok();
	}

}
