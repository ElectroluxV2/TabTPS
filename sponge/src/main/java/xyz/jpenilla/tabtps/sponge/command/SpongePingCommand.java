/*
 * This file is part of TabTPS, licensed under the MIT License.
 *
 * Copyright (c) 2020-2023 Jason Penilla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package xyz.jpenilla.tabtps.sponge.command;

import cloud.commandframework.context.CommandContext;
import cloud.commandframework.sponge.argument.MultiplePlayerSelectorArgument;
import cloud.commandframework.sponge.data.MultiplePlayerSelector;
import java.util.stream.Collectors;
import org.checkerframework.checker.nullness.qual.NonNull;
import xyz.jpenilla.tabtps.common.command.Commander;
import xyz.jpenilla.tabtps.common.command.Commands;
import xyz.jpenilla.tabtps.common.command.commands.PingCommand;
import xyz.jpenilla.tabtps.sponge.TabTPSPlugin;

public final class SpongePingCommand extends PingCommand {
  private final TabTPSPlugin plugin;

  public SpongePingCommand(final @NonNull TabTPSPlugin plugin, final @NonNull Commands commands) {
    super(plugin.tabTPS(), commands);
    this.plugin = plugin;
  }

  @Override
  public void register() {
    this.registerPingTargetsCommand(MultiplePlayerSelectorArgument.of("target"), this::handlePingTargets);
  }

  private void handlePingTargets(final @NonNull CommandContext<Commander> context) {
    final MultiplePlayerSelector target = context.get("target");
    this.pingTargets(
      context.getSender(),
      target.get().stream()
        .map(this.plugin.userService()::user)
        .collect(Collectors.toList()),
      target.inputString(),
      context.get("page")
    );
  }
}
